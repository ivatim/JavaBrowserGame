package main;

public class ResourceFactory {
    private static ResourceFactory resourceFactory;

    private ResourceFactory() {}

    public static ResourceFactory getInstance() {
        if (resourceFactory == null) {
            resourceFactory = new ResourceFactory();
        }
        return resourceFactory;
    }
}
