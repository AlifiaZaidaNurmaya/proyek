<?php

class transaksi extends CI_Controller {
    public function __construct()
    {
        parent::__construct();
        $this->load->model('transaksi_model');
        $this->load->model('entry_model');
        $this->load->model('petugas_model');
        $this->load->library('form_validation');
    }

    public function index()
    {
        $data['title']='Data Transaksi';
        $data['transaksi'] = $this->transaksi_model->getAllTransaksi();

        if ($this->input->post('keyword')) {
            $data['transaksi'] = $this->transaksi_model->cariDataTransaksi();
        }

        $this->load->view('template/adm_header',$data);
        $this->load->view('transaksi/index',$data);
        $this->load->view('template/adm_table_footer');

    }

    public function tambah() {
        $data['title'] = 'Form Menambahkan Data Transaksi';
        $data['transaksi'] = $this->transaksi_model->getAllTransaksi();
        $data['entry'] = $this->entry_model->getAllEntry();
        $data['petugas'] = $this->petugas_model->getAllPetugas();

        $this->form_validation->set_rules('id_transaksi', 'Id Transaksi', 'required');
        $this->form_validation->set_rules('id_entry', 'Id Entry', 'required');
        $this->form_validation->set_rules('jenis_transaksi', 'Jenis Transaksi', 'required');
        $this->form_validation->set_rules('jam_checkout', 'Jam Checkout', 'required');
        $this->form_validation->set_rules('total', 'Total', 'required');
        $this->form_validation->set_rules('id_petugas', 'Id_Petugas', 'required');

        if ($this->form_validation->run() == FALSE) {
            $this->load->view('template/adm_header', $data);
            $this->load->view('transaksi/tambah', $data);
            $this->load->view('template/adm_footer_form');
        }
        else {
            $this->transaksi_model->tambahDataTransaksi();
            $this->session->set_flashdata('flash-data','ditambahkan');
            redirect('transaksi','refresh');

        }
    }

    public function hapus($id) {
        $this->transaksi_model->hapusDataTransaksi($id);
        $this->session->set_flashdata('flash-data','dihapus');
        redirect('transaksi','refresh');
    }

    public function edit($id) {
        $data['title']= 'Form Edit Data Transaksi';
        $data['transaksi']=$this->transaksi_model->getTransaksiByID($id);

        $this->load->library('form_validation');
        $this->form_validation->set_rules('id_transaksi', 'Id Transaksi', 'required');
        $this->form_validation->set_rules('id_entry', 'Id Entry', 'required');
        $this->form_validation->set_rules('jenis_transaksi', 'Jenis Transaksi', 'required');
        $this->form_validation->set_rules('jam_checkout', 'Jam Checkout', 'required');
        $this->form_validation->set_rules('total', 'Total', 'required');
        $this->form_validation->set_rules('id_petugas', 'Id_Petugas', 'required');

        if ($this->form_validation->run() == FALSE) {
            $this->load->view('template/header', $data);
            $this->load->view('transaksi/edit', $data);
            $this->load->view('template/footer');
        } else {
            $this->transaksi_model->editDataTransaksi();
            $this->session->set_flashdata('flash-data','diedit');
            redirect('transaksi','refresh');
        }
    }
}

?>