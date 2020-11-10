<?php

class booking_model extends CI_Model {

    public function getAllBooking()
    {
        $this->db->select('id_booking,b.id_pelanggan,nama,nomor_plat,no_identitas,jam_booking,no_parkir');
        $this->db->from('booking b');
        $this->db->join('pelanggan p', 'b.id_pelanggan = p.id_pelanggan');
        $this->db->join('tempat_parkir tp', 'b.id_parkir = tp.id_parkir');
        $query = $this->db->get();
        return $query->result_array();
    }

    public function tambahDataBooking()
    {
        $data = [
            "id_pelanggan" => $this->input->post('id_pelanggan', true),
            "id_parkir" => $this->input->post('id_parkir', true),
            "jam_booking" => $this->input->post('jam_booking', true)
        ];
        $this->db->insert('booking', $data);
    }

    public function hapusDataBooking($id)
    {
        $this->db->where('id_booking', $id);
        $this->db->delete('booking');
    }

    public function getBookingByID($id)
    {
        $this->db->select('id_booking,b.id_pelanggan,nama,nomor_plat,no_identitas,jam_booking,no_parkir');
        $this->db->from('booking b');
        $this->db->join('pelanggan p', 'b.id_pelanggan = p.id_pelanggan');
        $this->db->join('tempat_parkir tp', 'b.id_parkir = tp.id_parkir');
        $this->db->where('id_booking', $id);
        
        return $this->db->get()->row_array();
    }
    
    public function getBookingByHurufAcak($hurufAcak)
    {
        $this->db->select('id_booking,b.id_pelanggan,nama,nomor_plat,no_identitas,jam_booking,b.id_parkir,no_parkir');
        $this->db->from('booking b');
        $this->db->join('pelanggan p', 'b.id_pelanggan = p.id_pelanggan');
        $this->db->join('tempat_parkir tp', 'b.id_parkir = tp.id_parkir');
        $this->db->where('p.huruf_acak', $hurufAcak);
        
        return $this->db->get()->row_array();
    }

    public function editDataBooking()
    {
        $data = [
            "id_parkir" => $this->input->post('id_parkir', true),
        ];
        $this->db->where('id_booking', $this->input->post('id_booking'));
        $this->db->update('booking', $data);
    }

    public function cariDataBooking()
    {
        $keyword = $this->input->post('keyword');
        $this->db->like('nama', $keyword);
        $this->db->or_like('id_booking', $keyword);
        $this->db->or_like('b.id_pelanggan', $keyword);
        $this->db->or_like('nomor_plat', $keyword);
        $this->db->or_like('no_identitas', $keyword);
        $this->db->or_like('jam_booking', $keyword);
        $this->db->or_like('no_parkir', $keyword);
        $this->db->from('booking b');
        $this->db->join('pelanggan p', 'b.id_pelanggan = p.id_pelanggan');
        $this->db->join('tempat_parkir tp', 'b.id_parkir = tp.id_parkir');
        $query = $this->db->get();
        return $query->result_array();
    }
}

?>