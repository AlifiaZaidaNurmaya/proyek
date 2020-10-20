<?php

class user extends CI_Controller {
    public function __construct()
    {
        parent::__construct();
        $this->load->model('user_model');
        $this->load->library('form_validation');
    }

    public function index()
    {
        $data['title']='Data Pelanggan';
        $data['user'] = $this->user_model->getAllUser();

        if ($this->input->post('keyword')) {
            $data['user'] = $this->user_model->cariDataUser();
        }

        $this->load->view('template/header',$data);
        $this->load->view('user/index',$data);
        $this->load->view('template/footer');

    }

    public function tambah() {
        $data['title'] = 'Form Menambahkan Data User';

        $this->load->library('form_validation');
        $this->form_validation->set_rules('id_pelanggan', 'Id_pelanggan', 'required');
        $this->form_validation->set_rules('nama', 'Nama', 'required');
        $this->form_validation->set_rules('password', 'Password', 'required');
        $this->form_validation->set_rules('alamat', 'Alamat', 'required');
        $this->form_validation->set_rules('nomor_plat', 'Nomor_plat', 'required');
        $this->form_validation->set_rules('nomor_telepon', 'Nomor_telepon', 'required');
        $this->form_validation->set_rules('no_identitas', 'No_identitas', 'required');
        $this->form_validation->set_rules('email', 'Email', 'required');
        $this->form_validation->set_rules('huruf_acak', 'Huruf_acak', 'required');

        if ($this->form_validation->run() == FALSE) {
            $this->load->view('template/header', $data);
            $this->load->view('user/tambah', $data);
            $this->load->view('template/footer');
        }
        else {
            $this->user_model->tambahDataUser();
            $this->session->set_flashdata('flash-data','ditambahkan');
            redirect('user','refresh');

        }
    }

    public function hapus($id) {
        $this->user_model->hapusDataUser($id);
        $this->session->set_flashdata('flash-data','dihapus');
        redirect('user','refresh');
    }

    public function edit($id) {
        $data['title']= 'Form Edit Data User';
        $data['user']=$this->user_model->getUserByID($id);

        $this->load->library('form_validation');
        $this->form_validation->set_rules('id_pelanggan', 'Id_pelanggan', 'required');
        $this->form_validation->set_rules('nama', 'Nama', 'required');
        $this->form_validation->set_rules('password', 'Password', 'required');
        $this->form_validation->set_rules('alamat', 'Alamat', 'required');
        $this->form_validation->set_rules('nomor_plat', 'Nomor_plat', 'required');
        $this->form_validation->set_rules('nomor_telepon', 'Nomor_telepon', 'required');
        $this->form_validation->set_rules('no_identitas', 'No_identitas', 'required');
        $this->form_validation->set_rules('email', 'Email', 'required');
        $this->form_validation->set_rules('huruf_acak', 'Huruf_acak', 'required');

        if ($this->form_validation->run() == FALSE) {
            $this->load->view('template/header', $data);
            $this->load->view('user/edit', $data);
            $this->load->view('template/footer');
        } else {
            $this->user_model->editDataUser();
            $this->session->set_flashdata('flash-data','diedit');
            redirect('user','refresh');
        }
    }
}

?>