package com.parivahan;
import com.parivahan.dao.db.ParivahanDao;
import com.parivahan.service.ParivahanService;

public class Driver {

    public static void main(String args[]) {
        ParivahanDao parivahanDaoInMemory = new ParivahanDao();
        ParivahanService parivahanService = new ParivahanService(parivahanDaoInMemory);

        parivahanService.addUser("Rohan", "M", 36);

    }
}
