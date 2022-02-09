package com.example.demo.service;

import com.example.demo.TestBase;
import com.example.demo.model.Sector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SectorServiceTest extends TestBase {
        @Autowired
        private SectorService sectorService;

        List<Sector> sectors;

        @BeforeEach
        public void init() {
               sectors = new ArrayList<>();
        }

        @Test
        void getAllSectorsShouldReturnSectorsListInSameOrder(){
                sectors.clear();
                sectors.addAll(getSectors());
                List<Sector> actualSectors = sectorService.getSectorList();
                assertEquals(sectors.size(), actualSectors.size()); //check size
                assertEquals(sectors.get(0), actualSectors.get(0)); //check order
        }

        private List<Sector> getSectors(){
                List<Sector> sectors = new ArrayList<>();
                List<Sector> result = new ArrayList<>();

                Sector sector1 = new Sector(6L, 1L, "Food and Beverage", false, null);
                Sector sector2 = new Sector(18L, 1L, "Electronics and Optics", false, null);
                Sector sector3 = new Sector(42L, 6L, "Fish and fish products", false, null);
                Sector sector4 = new Sector(342L, 6L, "Bakery and confectionery products", false, null);
                Sector sector5 = new Sector(43L, 6L, "Beverages", false, null);
                Sector sector6 = new Sector(1L, null, "Manufacturing", false, new ArrayList<>());


                sectors.add(sector1);
                sectors.add(sector2);
                sectors.add(sector3);
                sectors.add(sector4);
                sectors.add(sector5);
                sectors.add(sector6);

                List<Sector> rootElements = sectors.stream().filter(item -> item.getParentId() == null)
                    .sorted(Comparator.comparing(Sector::getName)).collect(Collectors.toList());
                result.addAll(rootElements);
                sectors.stream().forEach(item -> {
                        sectorService.populateResultList(sectors, result, item);
                });
                return result;
        }
}
