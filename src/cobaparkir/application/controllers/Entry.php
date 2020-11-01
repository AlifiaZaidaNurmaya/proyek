<?php 

defined('BASEPATH') OR exit('No direct script access allowed');

class Entry extends CI_Controller {

    public function __construct(){
        parent::__construct();
        $this->load->model('entry_model','entry');
        
    }

    public function index()
    {
        $data['title']='Data Entry';
        $data['entry'] = $this->entry->getAllEntry();

        if ($this->input->post('keyword')) {
            $data['entry'] = $this->entry->cariDataEntry();
        }

        $this->load->view('template/adm_header',$data);
        $this->load->view('entry/index',$data);
        $this->load->view('template/adm_table_footer');
    }

    // public function tambah() {
    //     $data['title'] = 'Form Menambahkan Data Booking';
    //     $data['pelanggan'] = $this->pelanggan_model->getAllPelanggan();
    //     $data['booking'] = $this->booking_model->getAllBooking();
    //     $data['parkir'] = $this->tempat_parkir->getAllParkir();

    //     $this->form_validation->set_rules('id_pelanggan', 'Id Pelanggan', 'required');
    //     $this->form_validation->set_rules('id_parkir', 'Nomor Parkir', 'required');
    //     $this->form_validation->set_rules('jam_booking', 'Jam Booking', 'required');

    //     if ($this->form_validation->run() == FALSE) {
    //         $this->load->view('template/adm_header', $data);
    //         $this->load->view('booking/tambah', $data);
    //         $this->load->view('template/adm_footer_form');
    //     }
    //     else {
    //         $this->booking_model->tambahDataBooking();
    //         $this->session->set_flashdata('flash-data','ditambahkan');
    //         redirect('booking','refresh');

    //     }
    // }

    public function hapus($id) {
        $this->entry->hapusDataEntry($id);
        $this->session->set_flashdata('flash-data','dihapus');
        redirect('entry','refresh');
    }

    public function edit($id) {
        $data['title']= 'Form Edit Data Entry';
        $data['entry']=$this->booking_model->getEntryByID($id);

        $this->load->library('form_validation');
        $this->form_validation->set_rules('id_parkir', 'Nomor Parkir', 'required');

        if ($this->form_validation->run() == FALSE) {
            $this->load->view('template/header', $data);
            $this->load->view('booking/edit', $data);
            $this->load->view('template/footer');
        } else {
            $this->booking_model->editDataBooking();
            $this->session->set_flashdata('flash-data','diedit');
            redirect('booking','refresh');
        }
    }

}

/* End of file Entry.php */

?>