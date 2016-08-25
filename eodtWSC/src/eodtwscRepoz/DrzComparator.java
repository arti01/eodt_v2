package eodtwscRepoz;

public class DrzComparator implements Comparable<DrzComparator> {

    private DrzPF drz;

    public DrzComparator(DrzPF p) {
        drz=p;
    }
    

    @Override
    public int compareTo(DrzComparator o) {
        return (o.getDrz().getType().compareToIgnoreCase(this.getDrz().getType()))*-1;
    }

    public DrzPF getDrz() {
        return drz;
    }

    public void setDrz(DrzPF drz) {
        this.drz = drz;
    }
    
    
}
