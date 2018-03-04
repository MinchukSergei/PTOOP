package by.bsuir.fksis.info.ptoop.plugin;

import by.bsuir.fksis.info.ptoop.console.ProductMenu;
import by.bsuir.fksis.info.ptoop.console.command.AbstractCommand;

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
    private List<ProductPlugin> productPlugins;
    private ProductMenu productMenu;

    public ProductPluginManager(List<String> jars) {
        this.jars = jars;
        productPlugins = new ArrayList<>();
        buildClassLoader();
        buildProductPlugins();
    }

    public ProductMenu getProductMenu() {
        return productMenu;
    }

    public void setProductMenu(ProductMenu productMenu) {
        this.productMenu = productMenu;
    }

    public URLClassLoader getUrlClassLoader() {
        return urlClassLoader;
    }

    public List<String> getJars() {
        return jars;
    }

    public List<ProductPlugin> getProductPlugins() {
        return productPlugins;
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

    private void buildProductPlugins() {
        for (String jar : jars) {
            ProductPlugin productPlugin = getProductPlugin(jar);
            if (productPlugin != null) {
                productPlugins.add(productPlugin);
            }
        }
    }

    @Override
    public List<AbstractCommand> getCommands() {
        List<AbstractCommand> commands = new ArrayList<>();
        for (ProductPlugin productPlugin : productPlugins) {
            commands.addAll(productPlugin.getCommands());
        }
        ProductPluginImpl productPlugin = new ProductPluginImpl();
        productPlugin.setProductPluginManager(this);
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
        ProductPluginImpl productPlugin = new ProductPluginImpl();
        productPlugin.setProductPluginManager(this);
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
