package com.example.demo.service;

import com.example.demo.TestBase;
import com.example.demo.model.Sector;

import com.example.demo.repository.SectorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SectorServiceTest extends TestBase {
        SectorRepository sectorRepository = Mockito.mock(SectorRepository.class);

        private final SectorService sectorService = new SectorService(sectorRepository);

        @Test
        void testSectorList(){
                Sector sector = new Sector();
                sector.setName("Kitchen");
                Mockito.when(sectorRepository.findAll()).thenReturn(List.of(sector));
                List<Sector> result = sectorService.getSectorList();
                Assertions.assertEquals(1, result.size());
        }

        @Test
        void getAllSectorsShouldReturnSectorsListInSameOrder(){
                List<Sector> sectors = getSectors();
                Mockito.when(sectorRepository.findAll()).thenReturn(sectors);
                List<Sector> actualSectors = sectorService.getSectorList();
                assertEquals(sectors.size(), actualSectors.size()); //check size
                assertEquals("Manufacturing", actualSectors.get(0).getName()); //check order
        }

        private List<Sector> getSectors(){
                Sector sector1 = new Sector(6L, 1L, "Food and Beverage", false);
                Sector sector2 = new Sector(18L, 1L, "Electronics and Optics", false);
                Sector sector3 = new Sector(42L, 6L, "Fish and fish products", false);
                Sector sector4 = new Sector(342L, 6L, "Bakery and confectionery products", false);
                Sector sector5 = new Sector(43L, 6L, "Beverages", false);
                Sector sector6 = new Sector(1L, null, "Manufacturing", false);

                return List.of(sector1, sector2, sector3, sector4, sector5, sector6);
        }
}
