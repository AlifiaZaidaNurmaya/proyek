<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Login_model extends CI_Model
{

    public function auth($username, $password)
    {
        $this->db->select('*');
        $this->db->from('super_admin');
        $this->db->where('username', $username);
        $this->db->where('password', $password);
        $query = $this->db->get();
        if ($query->num_rows() == 0) {
            return false;
        } else {
            return $query->result();
        }
    }
    
    public function login_pelanggan($email, $password)
    {
        $this->db->select('*');
        $this->db->from('pelanggan');
        $this->db->where('email', $email);
        $this->db->where('password', $password);
        $query = $this->db->get();
        if ($query->num_rows() == 0) {
            return false;
        } else {
            return $query->result_array();
        }
    }
    
    public function login_petugas($email, $password)
    {
        $this->db->select('*');
        $this->db->from('petugas');
        $this->db->where('email', $email);
        $this->db->where('password', $password);
        $query = $this->db->get();
        if ($query->num_rows() == 0) {
            return false;
        } else {
            return $query->result_array();
        }
    }

    public function register(){
    {
        $data = [
            "nama" => $this->input->post('nama'),
            "email" => $this->input->post('email'),
            "username" => $this->input->post('username'),
            "password" => $this->input->post('password'),
            "alamat" => $this->input->post('alamat'),
            "no_telepon" => $this->input->post('no_telepon')
        ];
        $this->db->insert('super_admin', $data);
    }
}
    
}

/* End of file login_model.php */
