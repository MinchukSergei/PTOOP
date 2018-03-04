package by.bsuir.fksis.info.ptoop.util;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Class finder
 */
public class ClassSearcher {

    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param packageName The base package
     * @param annotation Adds class to result if it is annotated with this
     * @return The classes
     * @throws ClassNotFoundException if class not found
     * @throws IOException read error
     */
    public static List<Class> getClasses(String packageName, Class<? extends Annotation> annotation)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName, annotation));
        }
        return classes;
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory   The base directory
     * @param annotation Adds class to result if it is annotated with this
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException if class not found
     */
    private static List<Class> findClasses(File directory, String packageName, Class<? extends Annotation> annotation) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName(), annotation));
            } else if (file.getName().endsWith(".class")) {
                Class clazz = Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
                if (clazz.isAnnotationPresent(annotation)) {
                    classes.add(clazz);
                }
            }
        }
        return classes;
    }
}
