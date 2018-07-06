package alessandrofook.contrato.service;

import alessandrofook.contrato.model.contrato.Contraparte;
import alessandrofook.contrato.model.contrato.Contrato;
import alessandrofook.contrato.model.contrato.Parcela;
import alessandrofook.contrato.repository.ContraparteRepository;
import alessandrofook.contrato.repository.ContratoRepository;
import alessandrofook.contrato.repository.ParcelaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContratoService {

  @Autowired
  private ContratoRepository contratoRepository;

  @Autowired
  private ContraparteRepository contraparteRepository;

  @Autowired
  private ParcelaRepository parcelaRepository;

  @Transactional
  public Contrato cadastrarContrato(Contrato contrato) {

    for (Parcela parcela : contrato.getParcelas()) {
      parcela = parcelaRepository.save(parcela);
    }

    for (Contraparte contraparte : contrato.getContrapartes()) {

      contraparte = contraparteRepository.save(contraparte);
    }
    return contratoRepository.save(contrato);
  }

  @Transactional
  public void removerContrato(Long id) {

    Contrato contrato = contratoRepository.getOne(id);

    for (Parcela parcela : contrato.getParcelas()) {
      parcelaRepository.delete(parcela);
    }

    for (Contraparte contraparte : contrato.getContrapartes()) {
      List<Contrato> contratosList = contratoRepository.findByContrapartesRegistro(contraparte.getRegistro());
      if(contratosList.size() == 1) contraparteRepository.delete(contraparte);
    }

    contratoRepository.delete(contrato);
  }

  public Contrato getContrato(Long id) {
    return contratoRepository.getOne(id);
  }
}
