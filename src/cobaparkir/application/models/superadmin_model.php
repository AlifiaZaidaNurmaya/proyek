<?php

class superadmin_model extends CI_Model {

    public function getAllAdmin()
    {
        $query = $this->db->get('super_admin');
        return $query->result_array();
    }

    public function tambahDataAdmin()
    {
        $data = [
            "id_admin" => $this->input->post('id_admin', true),
            "nama" => $this->input->post('nama', true),
            "email" => $this->input->post('email', true),
            "username" => $this->input->post('username', true),
            "password" => $this->input->post('password', true),
            "alamat" => $this->input->post('alamat', true),
            "no_telepon" => $this->input->post('no_telepon', true)
        ];
        $this->db->insert('super_admin', $data);
    }

    public function hapusDataAdmin($id)
    {
        $this->db->where('id_admin', $id);
        $this->db->delete('super_admin');
    }

    public function getAdminByID($id)
    {
        return $this->db->get_where('super_admin', ['id_admin' => $id])->row_array();
    }

    public function editDataAdmin()
    {
        $data = [
            "id_admin" => $this->input->post('id_admin', true),
            "nama" => $this->input->post('nama', true),
            "email" => $this->input->post('email', true),
            "password" => $this->input->post('password', true),
            "alamat" => $this->input->post('alamat', true),
            "no_telepon" => $this->input->post('no_telepon', true)
        ];
        $this->db->where('id_admin', $this->input->post('id_admin'));
        $this->db->update('super_admin', $data);
    }

    public function register()
    { 
            $data = array(
                "nama" => $this->input->post('nama', true),
                "email" => $this->input->post('email', true),
                "username" => $this->input->post('username', true),
                "password" => $this->input->post('password', true),
                "alamat" => $this->input->post('alamat', true),
                "no_telepon" => $this->input->post('no_telepon', true)
            );
            $this->db->insert('super_admin', $data);
        
    }

    public function cariDataAdmin()
    {
        $keyword = $this->input->post('keyword');
        $this->db->like('nama', $keyword);
        return $this->db->get('super_admin')->result_array();
    }
}

?>