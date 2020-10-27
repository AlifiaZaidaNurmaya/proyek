<?php

class petugas extends CI_Controller {
    public function __construct()
    {
        parent::__construct();
        $this->load->model('petugas_model');
        $this->load->library('form_validation');
    }

    public function index()
    {
        $data['title']='Data Petugas';
        $data['petugas'] = $this->petugas_model->getAllPetugas();

        if ($this->input->post('keyword')) {
            $data['petugas'] = $this->petugas_model->cariDataPetugas();
        }

        $this->load->view('template/header',$data);
        $this->load->view('petugas/index',$data);
        $this->load->view('template/footer');

    }

    public function tambah() {
        $data['title'] = 'Form Menambahkan Data Petugas';

        $this->load->library('form_validation');
        $this->form_validation->set_rules('id_petugas', 'Id_petugas', 'required');
        $this->form_validation->set_rules('nama', 'Nama', 'required');
        $this->form_validation->set_rules('email', 'Email', 'required');
        $this->form_validation->set_rules('password', 'Password', 'required');
        $this->form_validation->set_rules('alamat', 'Alamat', 'required');
        $this->form_validation->set_rules('no_telepon', 'no_telepon', 'required');

        if ($this->form_validation->run() == FALSE) {
            $this->load->view('template/header', $data);
            $this->load->view('petugas/tambah', $data);
            $this->load->view('template/footer');
        }
        else {
            $this->petugas_model->tambahDataPetugas();
            $this->session->set_flashdata('flash-data','ditambahkan');
            redirect('petugas','refresh');

        }
    }

    public function hapus($id) {
        $this->petugas_model->hapusDataPetugas($id);
        $this->session->set_flashdata('flash-data','dihapus');
        redirect('petugas','refresh');
    }

    public function edit($id) {
        $data['title']= 'Form Edit Data Petugas';
        $data['petugas']=$this->petugas_model->getPetugasByID($id);

        $this->load->library('form_validation');
        $this->form_validation->set_rules('id_petugas', 'Id_petugas', 'required');
        $this->form_validation->set_rules('nama', 'Nama', 'required');
        $this->form_validation->set_rules('email', 'Email', 'required');
        $this->form_validation->set_rules('password', 'Password', 'required');
        $this->form_validation->set_rules('alamat', 'Alamat', 'required');
        $this->form_validation->set_rules('no_telepon', 'no_telepon', 'required');

        if ($this->form_validation->run() == FALSE) {
            $this->load->view('template/header', $data);
            $this->load->view('petugas/edit', $data);
            $this->load->view('template/footer');
        } else {
            $this->petugas_model->editDataPetugas();
            $this->session->set_flashdata('flash-data','diedit');
            redirect('petugas','refresh');
        }
    }
}

?>