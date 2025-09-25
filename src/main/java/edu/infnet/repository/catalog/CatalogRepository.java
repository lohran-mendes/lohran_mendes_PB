package edu.infnet.repository.catalog;

import edu.infnet.repository.Repository;

import java.io.IOException;
import java.util.List;

public class CatalogRepository {
    static Repository repository = new Repository("itensDB");

    public static List<String[]> consultCatalog() throws IOException {
        return repository.getAll();
    }
}
