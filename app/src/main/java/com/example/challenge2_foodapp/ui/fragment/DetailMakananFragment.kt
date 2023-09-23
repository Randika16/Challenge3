package com.example.challenge2_foodapp.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.challenge2_foodapp.R
import com.example.challenge2_foodapp.databinding.FragmentDetailMakananBinding


class DetailMakananFragment : Fragment() {

    private var _binding: FragmentDetailMakananBinding? = null
    private val binding get() = _binding!!
    private var nilaiMakanan = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailMakananBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lokasiValue.setOnClickListener {
            val linkLokasiMakanan = arguments?.getString("linkLokasi")
            navigateToMaps(linkLokasiMakanan!!)
        }

        binding.apply {
            val namaMakanan = arguments?.getString("nama")
            val hargaMakanan = arguments?.getString("harga")
            val gambarMakanan = arguments?.getInt("gambar")
            val deskripsiMakanan = arguments?.getString("deskripsi")
            val lokasiMakanan = arguments?.getString("lokasi")

            tvMakananDetail.text = namaMakanan
            ivMakananDetail.setImageResource(gambarMakanan!!)
            tvHargaDetail.text = hargaMakanan
            tvDesc.text = deskripsiMakanan
            lokasiValue.text = lokasiMakanan
            button.text = getString(R.string.tambah_keranjang, hargaMakanan)
        }

        binding.ibMinus.setOnClickListener {
            if (nilaiMakanan <= 0) {
                Toast.makeText(requireContext(), "Tidak bisa kurang dari 0", Toast.LENGTH_SHORT)
                    .show()
            } else {
                nilaiMakanan--
                binding.tvJumlah.text = nilaiMakanan.toString()
            }
        }

        binding.ibPlus.setOnClickListener {
            nilaiMakanan++
            binding.tvJumlah.text = nilaiMakanan.toString()
        }

        binding.backButton.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.popBackStack()
        }

    }

    private fun navigateToMaps(data: String) {

        // Membuat Intent untuk membuka Google Maps
        val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(data))

        mapIntent.setPackage("com.google.android.apps.maps")

        // Memeriksa apakah Google Maps terinstal
        if (mapIntent.resolveActivity(requireActivity().packageManager) != null) {
            // Jika terinstal, buka Google Maps
            startActivity(mapIntent)
        } else {
            // Jika tidak terinstal, Anda dapat menangani kasus ini di sini, misalnya dengan menampilkan pesan kesalahan
            Toast.makeText(
                requireContext(),
                "Aplikasi Google Maps tidak terinstal.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}