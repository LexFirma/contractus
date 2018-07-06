package alessandrofook.contrato.service;

import alessandrofook.contrato.excecoes.ParcelasInvalidasException;
import alessandrofook.contrato.model.contrato.Contraparte;
import alessandrofook.contrato.model.contrato.Contrato;
import alessandrofook.contrato.model.contrato.Parcela;
import alessandrofook.contrato.repository.ContraparteRepository;
import alessandrofook.contrato.repository.ContratoRepository;
import alessandrofook.contrato.repository.ParcelaRepository;
import java.util.ArrayList;
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

    validarParcelas(contrato, contrato.getParcelas());

    parcelaRepository.saveAll(contrato.getParcelas());
    contraparteRepository.saveAll(contrato.getContrapartes());

    return contratoRepository.save(contrato);
  }

  private void validarParcelas(Contrato contrato, List<Parcela> parcelas) {
    double total = 0;

    for (Parcela parcela : parcelas) {
      total += parcela.getValor();
    }

    if (!(total == contrato.getTotal())) {
      throw new ParcelasInvalidasException();
    }

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

  public Parcela adimplirParcela(Long parcelaId) {

    Parcela parcela = parcelaRepository.getOne(parcelaId);
    parcela.setPago(true);

    return parcelaRepository.save(parcela);
  }

  @Transactional
  public Contrato editarParcelas(Long contratoId, List<Parcela> parcelas) {

    Contrato contrato = getContrato(contratoId);

    validarParcelas(contrato, parcelas);
    contrato.setParcelas(new ArrayList<>());
    contratoRepository.saveAndFlush(contrato);
    parcelaRepository.deleteAll(contrato.getParcelas());

    List<Parcela> parcelasArmazenadas = parcelaRepository.saveAll(parcelas);
    contrato.setParcelas(parcelasArmazenadas);
    return contratoRepository.saveAndFlush(contrato);
  }
}
