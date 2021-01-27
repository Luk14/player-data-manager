package me.lukasz.services;

import me.lukasz.presistance.domain.User;
import me.lukasz.presistance.dto.UserDTO;
import me.lukasz.presistance.repos.UserRepo;
import me.lukasz.utils.MyBeanUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService
{

    private UserRepo userRepo;
    private ModelMapper modelMapper;

    public UserService(UserRepo userRepo, ModelMapper modelMapper)
    {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    private UserDTO mapToDTO(User user)
    {
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> readAll()
    {
        return userRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public UserDTO getUser(int id)
    {
        return mapToDTO(userRepo.findById(id).isPresent() ? userRepo.getOne(id) : null);
    }

    public UserDTO createUser(User user)
    {
        return mapToDTO(userRepo.save(user));
    }

    public UserDTO updateUser(int id, User user)
    {
        if (!userRepo.existsById(id)) return null;
        User update = userRepo.findById(id).get();
        MyBeanUtil.mergeNotNull(user, update);
        return mapToDTO(userRepo.save(update));
    }

    public boolean deleteUser(int id)
    {
        userRepo.deleteById(id);
        return !userRepo.existsById(id);
    }

}
