<?php

class petugas_model extends CI_Model {

    public function getAllPetugas()
    {
        $query = $this->db->get('petugas');
        return $query->result_array();
    }

    public function tambahDataPetugas()
    {
        $data = [
            "id_petugas" => $this->input->post('id_petugas', true),
            "nama" => $this->input->post('nama', true),
            "email" => $this->input->post('email', true),
            "password" => $this->input->post('password', true),
            "alamat" => $this->input->post('alamat', true),
            "nomor_telepon" => $this->input->post('nomor_telepon', true)
        ];
        $this->db->insert('petugas', $data);
    }

    public function hapusDataPetugas($id)
    {
        $this->db->where('id_petugas', $id);
        $this->db->delete('petugas');
    }

    public function getPetugasByID($id)
    {
        return $this->db->get_where('petugas', ['id_petugas' => $id])->row_array();
    }

    public function editDataPetugas()
    {
        $data = [
            "id_petugas" => $this->input->post('id_petugas', true),
            "nama" => $this->input->post('nama', true),
            "email" => $this->input->post('email', true),
            "password" => $this->input->post('password', true),
            "alamat" => $this->input->post('alamat', true),
            "nomor_telepon" => $this->input->post('nomor_telepon', true)
        ];
        $this->db->where('id_petugas', $this->input->post('id_petugas'));
        $this->db->update('petugas', $data);
    }

    public function UpdatePetugas($id)
    {
        $data = [
            'nama' => $this->input->post('nama'),
            'password' => $this->input->post('password'),
            'alamat' => $this->input->post('alamat'),
            'nomor_telepon' => $this->input->post('nomor_telepon'),
            'email' => $this->input->post('email')
        ];

        $this->db->where('id_petugas', $id);
        $this->db->update('petugas', $data);
        return $this->db->affected_rows();
    }


    public function cariDataPetugas()
    {
        $keyword = $this->input->post('keyword');
        $this->db->like('nama', $keyword);
        return $this->db->get('petugas')->result_array();
    }
}

?>