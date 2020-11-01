<?php

class pelanggan extends CI_Controller {
    public function __construct()
    {
        parent::__construct();
        $this->load->model('pelanggan_model');
    }

    public function index()
    {
        $data['title']='Data Pelanggan';
        $data['pelanggan'] = $this->pelanggan_model->getAllPelanggan    ();

        if ($this->input->post('keyword')) {
            $data['pelanggan'] = $this->pelanggan_model->cariDataPelanggan();
        }

        $this->load->view('template/adm_header',$data);
        $this->load->view('pelanggan/index',$data);
        $this->load->view('template/adm_table_footer');

    }

    

    public function tambah() {
        $data['title'] = 'Form Menambahkan Data Pelanggan';

        $this->form_validation->set_rules('id_pelanggan', 'Id Pelanggan', 'required');
        $this->form_validation->set_rules('nama', 'Nama', 'required');
        $this->form_validation->set_rules('username', 'Username', 'required');
        $this->form_validation->set_rules('password', 'Password', 'required');
        $this->form_validation->set_rules('alamat', 'Alamat', 'required');
        $this->form_validation->set_rules('nomor_plat', 'Nomor Plat', 'required');
        $this->form_validation->set_rules('nomor_telepon', 'Nomor_telepon', 'required');
        $this->form_validation->set_rules('no_identitas', 'No Identitas', 'required');
        $this->form_validation->set_rules('email', 'Email', 'required');
        $this->form_validation->set_rules('huruf_acak', 'Huruf Acak','required');

        if ($this->form_validation->run() == FALSE) {
            $this->load->view('template/adm_header', $data);
            $this->load->view('pelanggan/tambah', $data);
            $this->load->view('template/adm_footer_form');
        }
        else {
            $this->pelanggan_model->tambahDataPelanggan();
            $this->session->set_flashdata('flash-data','ditambahkan');
            redirect('pelanggan','refresh');

        }
    }

    public function hapus($id) {
        $this->pelanggan_model->hapusDataPelanggan($id);
        $this->session->set_flashdata('flash-data','dihapus');
        redirect('pelanggan','refresh');
    }

    public function edit($id) {
        $data['title']= 'Form Edit Data Pelanggan';
        $data['pelanggan']=$this->pelanggan_model->getPelangganByID($id);

        $this->load->library('form_validation');
        $this->form_validation->set_rules('id_pelanggan', 'Id_pelanggan');
        $this->form_validation->set_rules('nama', 'Nama', 'required');
        $this->form_validation->set_rules('password', 'Password', 'required');
        $this->form_validation->set_rules('alamat', 'Alamat', 'required');
        $this->form_validation->set_rules('nomor_plat', 'Nomor_plat', 'required');
        $this->form_validation->set_rules('nomor_telepon', 'Nomor_telepon', 'required');
        $this->form_validation->set_rules('no_identitas', 'No_identitas', 'required');
        $this->form_validation->set_rules('email', 'Email', 'required');
        $this->form_validation->set_rules('huruf_acak', 'Huruf_acak');

        if ($this->form_validation->run() == FALSE) {
            $this->load->view('template/adm_header', $data);
            $this->load->view('pelanggan/edit', $data);
            $this->load->view('template/adm_footer_form');
        } else {
            $this->pelanggan_model->editDataPelanggan();
            $this->session->set_flashdata('flash-data','diedit');
            redirect('pelanggan','refresh');
        }
    }
}

?>