package gestion.gestionalimentos.serviceimpl;

import gestion.gestionalimentos.entity.Alimento;
import gestion.gestionalimentos.repository.AlimentoRepository;
import gestion.gestionalimentos.service.AlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlimentoServiceImpl implements AlimentoService {

    @Autowired
    private AlimentoRepository alimentoRepository;

    @Override
    public List<Alimento> getAllAlimentos() {
        return alimentoRepository.findAll();
    }

    @Override
    public Optional<Alimento> getAlimentoById(Long id) {
        return alimentoRepository.findById(id);
    }

    @Override
    public Alimento saveAlimento(Alimento alimento) {
        return alimentoRepository.save(alimento);
    }

    @Override
    public void deleteAlimento(Long id) {
        alimentoRepository.deleteById(id);
    }
}