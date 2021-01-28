package me.lukasz.services;

import me.lukasz.presistance.domain.Statistics;
import me.lukasz.presistance.dto.StatisticsDTO;
import me.lukasz.presistance.repos.StatisticsRepo;
import me.lukasz.utils.MyBeanUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsService
{

    private StatisticsRepo statisticsRepo;
    private ModelMapper modelMapper;

    public StatisticsService(StatisticsRepo statisticsRepo, ModelMapper modelMapper)
    {
        this.statisticsRepo = statisticsRepo;
        this.modelMapper = modelMapper;
    }

    private StatisticsDTO mapToDTO(Statistics statistics)
    {
        return modelMapper.map(statistics, StatisticsDTO.class);
    }

    public List<StatisticsDTO> readAll()
    {
        return statisticsRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public StatisticsDTO getStatistics(int id)
    {
        return mapToDTO(statisticsRepo.findById(id).isPresent() ? statisticsRepo.getOne(id) : null);
    }

    public StatisticsDTO createStatistics(Statistics statistics)
    {
        return mapToDTO(statisticsRepo.save(statistics));
    }

    public StatisticsDTO updateStatistics(int id, Statistics statistics)
    {
        if (!statisticsRepo.existsById(id)) return null;
        Statistics update = statisticsRepo.findById(id).get();
        MyBeanUtil.mergeNotNull(statistics, update);
        return mapToDTO(statisticsRepo.save(update));
    }

    public boolean deleteStatistics(int id)
    {
        statisticsRepo.deleteById(id);
        return !statisticsRepo.existsById(id);
    }
    
}
