<?php

class pelanggan_model extends CI_Model {

    public function getAllPelanggan()
    {
        $query = $this->db->get('pelanggan');
        return $query->result_array();
    }

    
    public function tambahDataPelanggan()
    {
        $data = [
            "id_pelanggan" => $this->input->post('id_pelanggan'),
            "nama" => $this->input->post('nama'),
            "username" => $this->input->post('username'),
            "password" => $this->input->post('password'),
            "alamat" => $this->input->post('alamat'),
            "nomor_plat" => $this->input->post('nomor_plat'),
            "nomor_telepon" => intval($this->input->post('nomor_telepon')),
            "no_identitas" => intval($this->input->post('no_identitas')),
            "email" => $this->input->post('email'),
            "huruf_acak" => $this->input->post('huruf_acak')
            
        ];
        $this->db->insert('pelanggan', $data);
    }

    public function hapusDataPelanggan($id)
    {
        $this->db->where('id_pelanggan', $id);
        $this->db->delete('pelanggan');
    }

    public function getPelangganByID($id)
    {
        return $this->db->get_where('pelanggan', ['id_pelanggan' => $id])->row_array();
    }

    public function editDataPelanggan()
    {
        $data = [
            "id_pelanggan" => $this->input->post('id_pelanggan', true),
            "nama" => $this->input->post('nama', true),
            "password" => $this->input->post('password', true),
            "alamat" => $this->input->post('alamat', true),
            "nomor_plat" => $this->input->post('nomor_plat', true),
            "nomor_telepon" => $this->input->post('nomor_telepon', true),
            "no_identitas" => $this->input->post('no_identitas', true),
            "email" => $this->input->post('email', true),
            "huruf_acak" => $this->input->post('huruf_acak', true)
        ];
        $this->db->where('id_pelanggan', $this->input->post('id_pelanggan'));
        $this->db->update('pelanggan', $data);
    }

    public function cariDataPelanggan()
    {
        $keyword = $this->input->post('keyword');
        $this->db->like('nama', $keyword);
        return $this->db->get('pelanggan')->result_array();
    }
}

?>