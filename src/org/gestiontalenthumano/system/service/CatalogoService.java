package org.gestiontalentoshumanos.system.service;

import org.gestiontalentoshumanos.system.model.Catalogo;
import org.gestiontalentoshumanos.system.repository.CatalogoRepository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogoService {

    private final CatalogoRepository catalogoRepository = new CatalogoRepository();

    public List<Catalogo> getPuestos() {
        try {
            return catalogoRepository.readPuestos();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Catalogo> getDepartamentos() {
        try {
            return catalogoRepository.readDepartamentos();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
