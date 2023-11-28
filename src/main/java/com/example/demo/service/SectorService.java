package com.example.demo.service;
import com.example.demo.model.Sector;
import com.example.demo.repository.SectorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class SectorService {

        private final SectorRepository sectorRepository;

        public List<Sector> getSectorList() {
            List<Sector> result = new ArrayList<>();
            List<Sector> list = sectorRepository.findAll();
            List<Sector> rootElements = list.stream().filter(item -> item.getParentId() == null)
                .sorted(Comparator.comparing(Sector::getName)).toList();

            result.addAll(rootElements);

            list.forEach(item -> populateResultList(list, result, item));

            return result;
        }


        public void populateResultList(List<Sector> list, List<Sector> result, Sector sector) {
                int indexOpt = 0;
                List<Sector> subList = list.stream()
                    .filter(item -> item.getParentId() != null && item.getParentId().equals(sector.getSectorId())).collect(
                    Collectors.toList());
                if (subList.size() > 0) {
                        if (sector.getParentId() != null) {
                                sector.setParentGroup(true);
                        }
                   subList.sort(Comparator.comparing(Sector::getName));
                   OptionalInt optionalInt = IntStream.range(0, result.size())
                            .filter(i -> sector.getSectorId().equals(result.get(i).getSectorId()))
                            .findFirst();
                   if (optionalInt.isPresent()) {
                           indexOpt = optionalInt.getAsInt();
                   }

                   result.addAll(indexOpt + 1, subList);
                }

        }
}
