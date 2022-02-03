package com.example.demo.service;
import com.example.demo.dao.ISectorDAO;
import com.example.demo.model.Sector;
import com.example.demo.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class SectorService implements ISectorDAO {
        @Autowired
        private SectorRepository sectorRepository;

        @Override
        public List<Sector> getSectorList() {
            List<Sector> result = new ArrayList<>();
            List<Sector> list = sectorRepository.findAll();
            List<Sector> rootElements = list.stream().filter(item -> item.getParentId() == null)
                .sorted(Comparator.comparing(Sector::getName)).collect(Collectors.toList());

            result.addAll(rootElements);

            list.stream().forEach(item -> {
                    populateResultList(list, result, item);
            });


            return result;
        }


        public List<Sector> populateResultList(List<Sector> list, List<Sector> result, Sector sector) {
                List<Sector> subList = list.stream()
                    .filter(item -> item.getParentId() != null && item.getParentId().equals(sector.getSectorId())).collect(
                    Collectors.toList());
                if (subList.size() > 0) {
                        if (sector.getParentId() != null) {
                                sector.setParentGroup(true);
                        }
                   subList.sort(Comparator.comparing(Sector::getName));
                        int indexOpt = IntStream.range(0, result.size())
                            .filter(i -> sector.getSectorId().equals(result.get(i).getSectorId()))
                            .findFirst().getAsInt();
                   result.addAll(indexOpt + 1, subList);
                }

                return result;
        }
}