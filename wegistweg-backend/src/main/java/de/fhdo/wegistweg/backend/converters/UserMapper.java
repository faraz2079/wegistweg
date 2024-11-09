package de.fhdo.wegistweg.backend.converters;

import de.fhdo.wegistweg.backend.domain.User;
import de.fhdo.wegistweg.backend.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDto entityToDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());

        return dto;
    }

    public List<UserDto> entityToDto(List<User> entities) {
        return entities.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}
