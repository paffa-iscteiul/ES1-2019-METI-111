package Projeto_METI_111.Projeto_METI_111;


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
	
	public Record(String methodID, String pack, String classe, String method, String loc, String cyclo,
			String atfd, String laa, String islongmethod, String iplasma, String pmd, String isfeatureenvy) {
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
	
	public Record() {}
	
	public String getMethodID() {
		return methodID;
	}
	
	public String getPack() {
		return pack;
	}
	
	public String getClasse() {
		return classe;
	}
	
	public String getMethod() {
		return method;
	}
	
	public String getLoc() {
		return loc;
	}
	
	public String getCyclo() {
		return cyclo;
	}
	
	public String getAtfd() {
		return atfd;
	}
	
	public String getLaa() {
		return laa;
	}
	
	public String getIsLongMethod() {
		return islongmethod;
	}
	
	public String getIPlasma() {
		return iplasma;
	}
	
	public String getPmd() {
		return pmd;
	}
	
	public String getIsFeatureEnvy() {
		return isfeatureenvy;
	}
	
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
	
	public String getRegisto() {
		return (methodID+" "+pack+" "+classe+" "+method+" "+loc+" "+cyclo+" "+atfd+" "+laa+" "+islongmethod
				+" "+iplasma+" "+pmd+" "+isfeatureenvy);
	}
			
}
