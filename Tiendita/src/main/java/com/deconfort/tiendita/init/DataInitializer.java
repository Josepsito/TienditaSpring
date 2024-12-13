package com.deconfort.tiendita.init;

import com.deconfort.tiendita.TienditaApplication;
import com.deconfort.tiendita.entity.Role;
import com.deconfort.tiendita.entity.Store;
import com.deconfort.tiendita.repository.RoleRepository;
import com.deconfort.tiendita.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {

        if (!storeRepository.existsById(1)) {
            Store store = new Store();
            store.setId_store(1);
            store.setTotal_money(0.0);
            store.setTotal_sale(0);
            storeRepository.save(store);
            System.out.println("Store no existe, por lo tanto se creara una intancia automaticamente");
        } else {
            System.out.println("Store encontrado");
        }

        if (roleRepository.count() == 0) {
            Role roleUser = new Role();
            roleUser.setName("ROLE_USER");

            System.out.println(roleUser.getUsers());

            Role roleAdmin = new Role();
            roleAdmin.setName("ROLE_ADMIN");

            roleRepository.save(roleUser);
            roleRepository.save(roleAdmin);

            System.out.println("Roles por defecto creados: ROLE_USER y ROLE_ADMIN");
        } else {
            System.out.println("Roles ya existentes en la base de datos");
        }

    }
}
