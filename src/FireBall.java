import java.util.Date;
import java.util.Objects;

public class FireBall {
	
    private Date date;
    private double energy;
    private double totalImpactEnergy;
    private double latitude;
    private String latDir;
    private double longitude;
    private String longDir;

    public FireBall() {
    	
    }

    public FireBall(double latitude, double longitude,
                    String latDir, String longDir) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.latDir = latDir;
        this.longDir = longDir;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getTotalImpactEnergy() {
        return totalImpactEnergy;
    }

    public void setTotalImpactEnergy(double totalImpactEnergy) {
        this.totalImpactEnergy = totalImpactEnergy;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getLatDir() {
        return latDir;
    }

    public void setLatDir(String latDir) {
        this.latDir = latDir;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getLongDir() {
        return longDir;
    }

    public void setLongDir(String longDir) {
        this.longDir = longDir;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FireBall)) return false;
        FireBall fireball = (FireBall) o;
        return Double.compare(fireball.getEnergy(), getEnergy()) == 0 &&
                Double.compare(fireball.getTotalImpactEnergy(), getTotalImpactEnergy()) == 0 &&
                Double.compare(fireball.getLatitude(), getLatitude()) == 0 &&
                Double.compare(fireball.getLongitude(), getLongitude()) == 0 &&
                Objects.equals(getDate(), fireball.getDate()) &&
                getLatDir() == fireball.getLatDir() &&
                getLongDir() == fireball.getLongDir();
    }


    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getEnergy(), getTotalImpactEnergy(), getLatitude(),
                getLatDir(), getLongitude(), getLongDir());
    }

}
