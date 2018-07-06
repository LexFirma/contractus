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

  /**
   * Método que realiza o cadastro do contrato no sistema, assim como de seus objetos compostos,
   * parcelas e contrapartes.
   *
   * @param contrato - Objeto do tipo contrato a ser armazenado.
   * @return Objeto do tipo Contrato conforme registrado no sistema, e seus objetos compostos.
   */
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

  /**
   * Método que remove do sistema um contrato e seus objetos relacionados, caso não estejam
   * vinculados a outros contratos.
   *
   * @param id - id do contrato a ser removido.
   */
  @Transactional
  public void removerContrato(Long id) {

    Contrato contrato = contratoRepository.getOne(id);

    for (Parcela parcela : contrato.getParcelas()) {
      parcelaRepository.delete(parcela);
    }

    for (Contraparte contraparte : contrato.getContrapartes()) {
      String registro = contraparte.getRegistro();
      List<Contrato> contratosList = contratoRepository.findByContrapartesRegistro(registro);
      if (contratosList.size() == 1) {
        contraparteRepository.delete(contraparte);
      }
    }

    contratoRepository.delete(contrato);
  }

  public Contrato getContrato(Long id) {
    return contratoRepository.getOne(id);
  }
}
