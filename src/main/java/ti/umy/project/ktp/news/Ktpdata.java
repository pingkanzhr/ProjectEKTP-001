/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ti.umy.project.ktp.news;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "ktpdata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ktpdata.findAll", query = "SELECT k FROM Ktpdata k"),
    @NamedQuery(name = "Ktpdata.findById", query = "SELECT k FROM Ktpdata k WHERE k.id = :id"),
    @NamedQuery(name = "Ktpdata.findByNoktp", query = "SELECT k FROM Ktpdata k WHERE k.noktp = :noktp"),
    @NamedQuery(name = "Ktpdata.findByNama", query = "SELECT k FROM Ktpdata k WHERE k.nama = :nama"),
    @NamedQuery(name = "Ktpdata.findByTglahir", query = "SELECT k FROM Ktpdata k WHERE k.tglahir = :tglahir"),
    @NamedQuery(name = "Ktpdata.findByJenisKel", query = "SELECT k FROM Ktpdata k WHERE k.jenisKel = :jenisKel"),
    @NamedQuery(name = "Ktpdata.findByAlamat", query = "SELECT k FROM Ktpdata k WHERE k.alamat = :alamat"),
    @NamedQuery(name = "Ktpdata.findByAgama", query = "SELECT k FROM Ktpdata k WHERE k.agama = :agama"),
    @NamedQuery(name = "Ktpdata.findByStatus", query = "SELECT k FROM Ktpdata k WHERE k.status = :status"),
    @NamedQuery(name = "Ktpdata.findByPekerjaan", query = "SELECT k FROM Ktpdata k WHERE k.pekerjaan = :pekerjaan"),
    @NamedQuery(name = "Ktpdata.findByWargaNegara", query = "SELECT k FROM Ktpdata k WHERE k.wargaNegara = :wargaNegara"),
    @NamedQuery(name = "Ktpdata.findByBerlakuHingga", query = "SELECT k FROM Ktpdata k WHERE k.berlakuHingga = :berlakuHingga")})
public class Ktpdata implements Serializable {

    @Lob
    @Column(name = "foto")
    private byte[] foto;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "noktp")
    private String noktp;
    @Basic(optional = false)
    @Column(name = "nama")
    private String nama;
    @Basic(optional = false)
    @Column(name = "tglahir")
    @Temporal(TemporalType.DATE)
    private Date tglahir;
    @Basic(optional = false)
    @Column(name = "jenis_kel")
    private String jenisKel;
    @Basic(optional = false)
    @Column(name = "alamat")
    private String alamat;
    @Basic(optional = false)
    @Column(name = "agama")
    private String agama;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Column(name = "pekerjaan")
    private String pekerjaan;
    @Basic(optional = false)
    @Column(name = "warga_negara")
    private String wargaNegara;
    @Column(name = "berlaku_hingga")
    private String berlakuHingga;

    public Ktpdata() {
    }

    public Ktpdata(Long id) {
        this.id = id;
    }

    public Ktpdata(Long id, String noktp, String nama, Date tglahir, String jenisKel, String alamat, String agama, String status, String wargaNegara) {
        this.id = id;
        this.noktp = noktp;
        this.nama = nama;
        this.tglahir = tglahir;
        this.jenisKel = jenisKel;
        this.alamat = alamat;
        this.agama = agama;
        this.status = status;
        this.wargaNegara = wargaNegara;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoktp() {
        return noktp;
    }

    public void setNoktp(String noktp) {
        this.noktp = noktp;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTglahir() {
        return tglahir;
    }

    public void setTglahir(Date tglahir) {
        this.tglahir = tglahir;
    }

    public String getJenisKel() {
        return jenisKel;
    }

    public void setJenisKel(String jenisKel) {
        this.jenisKel = jenisKel;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getWargaNegara() {
        return wargaNegara;
    }

    public void setWargaNegara(String wargaNegara) {
        this.wargaNegara = wargaNegara;
    }

    public String getBerlakuHingga() {
        return berlakuHingga;
    }

    public void setBerlakuHingga(String berlakuHingga) {
        this.berlakuHingga = berlakuHingga;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ktpdata)) {
            return false;
        }
        Ktpdata other = (Ktpdata) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ti.umy.project.ktp.news.Ktpdata[ id=" + id + " ]";
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    
}
