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
				op="OR";
				metricas1 = metricas.replace("OR ", "");
			}
			if(metricas.contains("AND")) {
				op="AND";
				metricas1 = metricas.replace("AND ", "");
			}
			
			String aux[] = metricas1.split(" ");
			String metricas2[] = new String [aux.length / 3];
				for(int i = 0; i<aux.length;i=i+3) {
					metricas2[i/3]= aux[i]+aux[i+1]+aux[i+2];
				}
				vetor_metricas=metricas2 ;
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
