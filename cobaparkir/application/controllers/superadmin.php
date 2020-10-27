<?php

class superadmin extends CI_Controller {
    public function __construct()
    {
        parent::__construct();
        $this->load->model('superadmin_model');
        $this->load->library('form_validation');
    }

    public function index()
    {
        $data['title']='Data Admin';
        $data['super_admin'] = $this->superadmin_model->getAllAdmin();

        if ($this->input->post('keyword')) {
            $data['super_admin'] = $this->superadmin_model->cariDataAdmin();
        }

        $this->load->view('template/adm_header',$data);
        $this->load->view('superadmin/index',$data);
        $this->load->view('template/adm_table_footer');

    }

    public function tambah() {
        $data['title'] = 'Form Menambahkan Data Admin';

        $this->load->library('form_validation');
        $this->form_validation->set_rules('id_admin', 'Id_admin', 'required');
        $this->form_validation->set_rules('nama', 'Nama', 'required');
        $this->form_validation->set_rules('email', 'Email', 'required');
        $this->form_validation->set_rules('password', 'Password', 'required');
        $this->form_validation->set_rules('alamat', 'Alamat', 'required');
        $this->form_validation->set_rules('no_telepon', 'no_telepon', 'required');

        if ($this->form_validation->run() == FALSE) {
            $this->load->view('template/adm_header', $data);
            $this->load->view('superadmin/tambah', $data);
            $this->load->view('template/adm_footer_form');
        }
        else {
            $this->superadmin_model->tambahDataAdmin();
            $this->session->set_flashdata('flash-data','ditambahkan');
            redirect('superadmin','refresh');

        }
    }

    public function hapus($id) {
        $this->superadmin_model->hapusDataAdmin($id);
        $this->session->set_flashdata('flash-data','dihapus');
        redirect('superadmin','refresh');
    }

    public function edit($id) {
        $data['title']= 'Form Edit Data Admin';
        $data['super_admin']=$this->superadmin_model->getAdminByID($id);

        $this->load->library('form_validation');
        $this->form_validation->set_rules('id_admin', 'Id_admin', 'required');
        $this->form_validation->set_rules('nama', 'Nama', 'required');
        $this->form_validation->set_rules('email', 'Email', 'required');
        $this->form_validation->set_rules('password', 'Password', 'required');
        $this->form_validation->set_rules('alamat', 'Alamat', 'required');
        $this->form_validation->set_rules('no_telepon', 'no_telepon', 'required');

        if ($this->form_validation->run() == FALSE) {
            $this->load->view('template/adm_header', $data);
            $this->load->view('superadmin/edit', $data);
            $this->load->view('template/adm_footer_form');
        } else {
            $this->superadmin_model->editDataAdmin();
            $this->session->set_flashdata('flash-data','diedit');
            redirect('superadmin','refresh');
        }
    }
}

?>