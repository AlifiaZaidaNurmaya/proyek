<?php 
defined('BASEPATH') OR exit('No direct script access allowed');

class entry_model extends CI_Model {

    public function getAllEntry()
    {
        $this->db->select('id_entry,e.id_pelanggan,e.id_booking,p.nama,p.nomor_plat,p.no_identitas,b.jam_booking,jam_entry,harga_perjam,durasi_entry,jam_checkout,b.id_parkir,tp.no_parkir');
        $this->db->from('entry e');
        $this->db->join('booking b', 'b.id_booking = e.id_booking');
        $this->db->join('pelanggan p', 'p.id_pelanggan = e.id_pelanggan');
        $this->db->join('tempat_parkir tp', 'tp.id_parkir = e.id_parkir');
        $query = $this->db->get();
        return $query->result_array();
    }

    public function tambahDataEntry($data)
    {
        $this->db->insert('entry', $data);
        return $this->db->affected_rows();
        
    }

    public function hapusDataEntry($id)
    {
        $this->db->where('id_entry', $id);
        $this->db->delete('entry');
    }
    
    public function deleteDataEntryByID($id)
    {
        $this->db->where('id_entry', $id);
        $this->db->delete('entry');
        return $this->db->affected_rows();
        
    }

    public function getEntryByID($id)
    {
        $this->db->select('id_entry,e.id_pelanggan,e.id_booking,p.nama,p.nomor_plat,p.no_identitas,b.jam_booking,jam_entry,harga_perjam,durasi_entry,jam_checkout,b.id_parkir as no_parkir_booking,tp.no_parkir as no_parkir_asli,');
        $this->db->from('entry e');
        $this->db->join('pelanggan p', 'e.id_pelanggan = p.id_pelanggan');
        $this->db->join('booking b', 'e.id_booking = b.id_booking');
        $this->db->join('tempat_parkir tp', 'e.id_parkir = tp.id_parkir');
        $this->db->join('tempat_parkir tpk', 'b.id_parkir = tpk.id_parkir');
        return $this->db->where('id_entry',$id)->row_array();
    }

    public function getEntryByHurufAcak($hurufAcak){
        $this->db->select('*');
        $this->db->from('entry e');
        $this->db->join('pelanggan p', 'e.id_pelanggan = p.id_pelanggan');
        $this->db->where('p.huruf_acak', $hurufAcak);
        return $this->db->get()->result_array();
    }


    public function editDataEntry()
    {
        $data = [
            "id_booking" => $this->input->post('id_booking', true),
            "id_pelanggan" => $this->input->post('id_pelanggan', true),
            "no_parkir" => $this->input->post('no_parkir', true),
            "jam_booking" => $this->input->post('jam_booking', true)
        ];
        $this->db->where('id_booking', $this->input->post('id_booking'));
        $this->db->update('booking', $data);
    }

    public function cariDataEntry()
    {
        $keyword = $this->input->post('keyword');
        $this->db->like('nama', $keyword);
        return $this->db->get('entry')->result_array();
    }

}

/* End of file entry_model.php */
