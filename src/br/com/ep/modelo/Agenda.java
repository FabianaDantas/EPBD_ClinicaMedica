package br.com.ep.modelo;

public class Agenda {
	private int idagenda;
	private String diaDaSemana;
	private String horaInicio;
	private String horaFim;
	private Medico medico;
	
	public int getIdagenda() {
		return idagenda;
	}
	public void setIdagenda(int idagenda) {
		this.idagenda = idagenda;
	}
	public String getDiaDaSemana() {
		return diaDaSemana;
	}
	public void setDiaDaSemana(String diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	
	
}
