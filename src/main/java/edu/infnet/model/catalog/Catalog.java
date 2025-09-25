package edu.infnet.model.catalog;

import edu.infnet.model.item.Item;
import edu.infnet.repository.catalog.CatalogRepository;

import java.io.IOException;
import java.util.List;

public class Catalog {
    private Item[] items;

    public List<String[]> consultCatalog() throws IOException {
        return CatalogRepository.consultCatalog();
    }
}
