<?php

class user_model extends CI_Model {

    public function getAllUser()
    {
        $query = $this->db->get('user');
        return $query->result_array();
    }

    public function tambahDataUser()
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
        $this->db->insert('user', $data);
    }

    public function hapusDataUser($id)
    {
        $this->db->where('id_pelanggan', $id);
        $this->db->delete('user');
    }

    public function getUserByID($id)
    {
        return $this->db->get_where('user', ['id_pelanggan' => $id])->row_array();
    }

    public function editDataUser()
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
        $this->db->update('user', $data);
    }

    public function cariDataUser()
    {
        $keyword = $this->input->post('keyword');
        $this->db->like('nama', $keyword);
        return $this->db->get('user')->result_array();
    }
}

?>