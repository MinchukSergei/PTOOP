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

    public ProductPluginManager(List<String> jars) {
        this.jars = jars;
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
            URLClassLoader jarURL = new URLClassLoader(new URL[] {jarFile.toURI().toURL()}, this.getClass().getClassLoader());
            String productImplPackage = "by.bsuir.fksis.info.ptoop.plugin";
            String productImplPath = productImplPackage + "." + jarFile.getName().substring(0, jarFile.getName().length() - 4);
            return (ProductPlugin) Class.forName( productImplPath + "Impl", true, jarURL).newInstance();
        } catch (ClassNotFoundException ignored) {
            System.err.println(jar + " was not found.");
        } catch (IllegalAccessException | InstantiationException | MalformedURLException e) {
            System.err.println(jar + " has a problem while creating plugin implementation: jarname + Impl.class");
        }
        return null;
    }
}
