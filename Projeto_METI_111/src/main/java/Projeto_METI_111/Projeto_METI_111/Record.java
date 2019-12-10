package Projeto_METI_111.Projeto_METI_111;

import java.io.IOException;

/**
* Esta classe permite definir uma estrutura de dados para os registos do ficheiro Excel
*
* @author  Pedro Fonseca, F�bio Cardoso, Filipa Gomes, In�s Gomes, Sofia P�rsio, Marco Silva
*/
public class Record {
	String methodID;
	String pack;
	String classe;
	String method;
	String loc;
	String cyclo;
	String atfd;
	String laa;
	String islongmethod;
	String iplasma;
	String pmd;
	String isfeatureenvy;
	
	/**
	 * Construtor da classe
	 * Recebe os v�rios valores para os atributos, ou seja, os v�rios valores para cada coluna do ficheiro Excel
	 * @param methodID id do m�todo
	 * @param pack package do m�todo
	 * @param classe classe do m�todo
	 * @param method m�todo
	 * @param loc linhas de c�digo do m�todo
	 * @param cyclo complexidade ciclom�tca do m�todo
	 * @param atfd acessos do m�todo a m�todos de outras classes
	 * @param laa acessos do m�todo a atributos da pr�pria classe
	 * @param islongmethod se o m�todo � longo
	 * @param iplasma classifica��o da ferramenta de dete�ao de defeitos iplasma
	 * @param pmd classifica��o da ferramenta de dete�ao de defeitos pmd
	 * @param isfeatureenvy se o m�todo � featureenvy
	 * @throws IOException importante
	 */
	public Record(String methodID, String pack, String classe, String method, String loc, String cyclo,
			String atfd, String laa, String islongmethod, String iplasma, String pmd, String isfeatureenvy) throws IOException {
		this.methodID=methodID;
		this.pack=pack;
		this.classe=classe;
		this.method=method;
		this.loc=loc;
		this.cyclo=cyclo;
		this.atfd=atfd;
		this.laa=laa;
		this.islongmethod=islongmethod;
		this.iplasma=iplasma;
		this.pmd=pmd;
		this.isfeatureenvy=isfeatureenvy;
	}
	
	/**
	 * Construtor da classe
	 * Serve para criar uma inst�ncia, cujos atributos se encontram vazios
	 * @throws IOException importante
	 */
	public Record() throws IOException {}
	
	/**
	 * getMethodID() Ir� retornar o ID do m�todo do registo em causa (linha do excel)
	 * @return methodID, ID do m�todo
	 */
	public String getMethodID() {
		return methodID;
	}
	
	/**
	 * getPack() Ir� retornar o Package do m�todo do registo em causa (linha do excel)
	 * @return pack, Package do m�todo
	 */
	public String getPack() {
		return pack;
	}
	
	/**
	 * getClasse() Ir� retornar a classe do m�todo do registo em causa (linha do excel)
	 * @return classe, classe do m�todo
	 */
	public String getClasse() {
		return classe;
	}
	
	/**
	 * getMethod() Ir� retornar o m�todo do registo em causa (linha do excel)
	 * @return method, m�todo
	 */
	public String getMethod() {
		return method;
	}
	
	/**
	 * getLoc() Ir� retornar linhas de c�digo do m�todo do registo em causa (linha do excel)
	 * @return loc, linhas de c�digo do m�todo
	 */
	public String getLoc() {
		return loc;
	}
	
	/**
	 * getCyclo() Ir� retornar a complexidade ciclom�tica do m�todo do registo em causa (linha do excel)
	 * @return cyclo, complexidade ciclom�tica do m�todo
	 */
	public String getCyclo() {
		return cyclo;
	}
	
	/**
	 * getAtfd() Ir� retornar o n�mero de acessos do m�todo do registo em causa 
	 * (linha do excel) a m�todo exteriores
	 * @return atfd, atfd do m�todo
	 */
	public String getAtfd() {
		return atfd;
	}
	
	/**
	 * getLaa() Ir� retornar o n�mero de acessos do m�todo do registo em causa 
	 * (linha do excel) a atributos da pr�pria classe
	 * @return laa, laa do m�todo
	 */
	public String getLaa() {
		return laa;
	}
	
	/**
	 * getIsLongMethod() Ir� retornar se o m�todo do registo em causa (linha do excel) � longo m�todo
	 * @return islongmethod, se o m�todo � longo (True or False)
	 */
	public String getIsLongMethod() {
		return islongmethod;
	}
	
	/**
	 * getIplasma() Ir� retornar a classifica��o da ferramente iPlasma sobre 
	 * o m�todo do registo em causa (linha do excel)
	 * @return iplasma, classifica��o iplasma sobre m�todo
	 */
	public String getIPlasma() {
		return iplasma;
	}
	
	/**
	 * getPmd() Ir� retornar a classifica��o da ferramente PMD sobre 
	 * o m�todo do registo em causa (linha do excel)
	 * @return pmd, classifica��o PMD sobre m�todo
	 */
	public String getPmd() {
		return pmd;
	}
	
	/**
	 * getIsFeatureEnvy() Ir� retornar se o m�todo do registo em causa (linha do excel) � feature_envy
	 * @return isfeatureenvy, se o m�todo � feature_envy (True or False)
	 */
	public String getIsFeatureEnvy() {
		return isfeatureenvy;
	}
	
	/**
	 * add() � uma fun��o que armazena a informa��o s, segundo determinado indice
	 * @param i indice
	 * @param s valor de atributo
	 */
	public void add(int i, String s) {
		if(i==1) methodID=s;
		if(i==2) pack=s;
		if(i==3) classe=s;
		if(i==4) method=s;
		if(i==5) loc=s;
		if(i==6) cyclo=s;
		if(i==7) atfd=s;
		if(i==8) laa=s;
		if(i==9) islongmethod=s;
		if(i==10) iplasma=s;
		if(i==11) pmd=s;
		if(i==12) isfeatureenvy=s;
	}
	
	/**
	 * getRegisto() Ir� retornar o registo do m�todo em causa (linha do excel)
	 * @return (methodID+" "+pack+" "+classe+" "+method+" "+loc+" "+cyclo+" "+atfd+" 
	 * "+laa+" "+islongmethod+" "+iplasma+" "+pmd+" "+isfeatureenvy), registo do m�todo (linha do excel)
	 */
	public String getRegisto() {
		return (methodID+" "+pack+" "+classe+" "+method+" "+loc+" "+cyclo+" "+atfd+" "+laa+" "+islongmethod
				+" "+iplasma+" "+pmd+" "+isfeatureenvy);
	}
			
}
