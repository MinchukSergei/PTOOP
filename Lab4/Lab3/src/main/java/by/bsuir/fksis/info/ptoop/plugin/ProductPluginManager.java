package by.bsuir.fksis.info.ptoop.plugin;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Product plugin implementation to retrieve commands and products from jars
 */
public class ProductPluginManager implements ProductPlugin {
    private List<String> jars;
    private URLClassLoader urlClassLoader;

    public ProductPluginManager(List<String> jars) {
        this.jars = jars;
        buildClassLoader();
    }

    public URLClassLoader getUrlClassLoader() {
        return urlClassLoader;
    }

    private void buildClassLoader() {
        List<URL> urls = new ArrayList<>();
        for (String jar : jars) {
            File jarFile = new File(jar);
            try {
                urls.add(jarFile.toURI().toURL());
            } catch (MalformedURLException e) {
                System.err.println(jarFile + " is invalid.");
            }
        }
        urlClassLoader = new URLClassLoader(urls.toArray(new URL[urls.size()]), this.getClass().getClassLoader());
    }

    @Override
    public List<Class> getCommands() {
        List<Class> commands = new ArrayList<>();
        for (String jar : jars) {
            ProductPlugin productPlugin = getProductPlugin(jar);
            if (productPlugin != null) {
                commands.addAll(productPlugin.getCommands());
            }
        }
        ProductPlugin productPlugin = new ProductPluginImpl();
        commands.addAll(productPlugin.getCommands());
        return commands;
    }

    @Override
    public List<Class> getProducts() {
        List<Class> products = new ArrayList<>();
        for (String jar : jars) {
            ProductPlugin productPlugin = getProductPlugin(jar);
            if (productPlugin != null) {
                products.addAll(productPlugin.getProducts());
            }
        }
        ProductPlugin productPlugin = new ProductPluginImpl();
        products.addAll(productPlugin.getProducts());
        return products;
    }

    private ProductPlugin getProductPlugin(String jar) {
        try {
            File jarFile = new File(jar);
            String productImplPackage = "by.bsuir.fksis.info.ptoop.plugin";
            String productImplPath = productImplPackage + "." + jarFile.getName().substring(0, jarFile.getName().length() - 4);
            return (ProductPlugin) Class.forName( productImplPath + "Impl", true, urlClassLoader).newInstance();
        } catch (ClassNotFoundException ignored) {
            System.err.println(jar + " was not found.");
        } catch (IllegalAccessException | InstantiationException e) {
            System.err.println(jar + " has a problem while creating plugin implementation: jarname + Impl.class");
        }
        return null;
    }
}
