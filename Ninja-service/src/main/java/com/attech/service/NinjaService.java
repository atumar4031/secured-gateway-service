package com.attech.service;


import com.attech.dto.NinjaRequest;
import com.attech.dto.NinjaResponse;
import com.attech.entity.Ninja;
import com.attech.repo.NinjaRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class NinjaService {
    private final NinjaRepo ninjaRepo;

    public NinjaResponse createNinja(NinjaRequest request) {
        Ninja ninja = Ninja.builder()
                .name(request.name())
                .age(request.age())
                .level(request.level())
                .build();

        Ninja save = ninjaRepo.save(ninja);
        return NinjaResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .level(save.getLevel())
                .age(save.getAge())
                .build();
    }

    public NinjaResponse updateNinja(long id, NinjaRequest request) {
        Ninja ninja = ninjaRepo.findById(id).orElse(null);
        if (ninja != null){
            ninja.setName(request.name());
            ninja.setAge(request.age());
            ninja.setLevel(request.level());
            Ninja save = ninjaRepo.save(ninja);

            return NinjaResponse.builder()
                    .id(save.getId())
                    .name(save.getName())
                    .level(save.getLevel())
                    .age(save.getAge())
                    .build();
        }
        return null;
    }

    public List<NinjaResponse> findNinja() {
        return  ninjaRepo.findAll().stream().map(ninja -> {
            return new NinjaResponse(ninja.getId(), ninja.getName(), ninja.getAge(), ninja.getLevel());
        }).collect(Collectors.toList());
    }

    public NinjaResponse deleteNinja(long id) {
        Ninja ninja = ninjaRepo.findById(id).orElse(null);
        if (ninja != null){
            ninjaRepo.delete(ninja);
            return NinjaResponse.builder()
                    .id(ninja.getId())
                    .name(ninja.getName())
                    .level(ninja.getLevel())
                    .age(ninja.getAge())
                    .build();
        }

        return null;
    }
}
