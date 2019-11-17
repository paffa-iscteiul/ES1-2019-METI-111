package Projeto_METI_111.Projeto_METI_111;


public class Regra {
	public int numero_de_regras;
	public String[] vetor_metricas;
	public String entao;
	public String senao;
	public String op;
	public String metricas;
	
	
	
	public Regra (String metricas, String entao, String senao ) {
		this.entao = entao;
		this.senao=senao;
		this.metricas=metricas;
		
		analisar_parametros(metricas);
		
	}
	
	public void analisar_parametros(String metricas) {
		numero_de_regras=count_ocorrences(metricas);
		String metricas1 = metricas;
			if(metricas.contains("OR")) {
				
			}
		
		
	}
	private int count_ocorrences(String m) {
		String a1[]=m.split(" ");
		int n= a1.length;
		int count = 1 ;
			for(int i = 0; i <n; i++) {
				if(a1[i].equals("AND")|| a1[i].equals("OR")) {
					count ++ ;
				}
			}
		
		
		return count;
	}
	
}
