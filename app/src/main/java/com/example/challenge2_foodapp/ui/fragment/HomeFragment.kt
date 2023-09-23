package com.example.challenge2_foodapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge2_foodapp.R
import com.example.challenge2_foodapp.adapter.MakananAdapter
import com.example.challenge2_foodapp.data.Makanan
import com.example.challenge2_foodapp.data.dataMakanan
import com.example.challenge2_foodapp.databinding.FragmentHomeBinding
import com.example.challenge2_foodapp.utils.GridSpacingItemDecoration

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var makananAdapter: MakananAdapter
    private lateinit var recyclerView: RecyclerView
    private var isListView = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.rvMakanan
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        makananAdapter = MakananAdapter(dataMakanan.dummyMakanan, requireContext())
        recyclerView.adapter = makananAdapter

        binding.listButton.setOnClickListener {
            isListView = !isListView
            updateListButtonImage()

            if (isListView) {
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                recyclerView.setHasFixedSize(true)
                makananAdapter.isListView = true

                // Hapus semua dekorasi yang ada
                val itemDecorations = recyclerView.itemDecorationCount
                for (i in 0 until itemDecorations) {
                    recyclerView.removeItemDecorationAt(0)
                }

            } else {
                recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                recyclerView.setHasFixedSize(true)
                val spacing = resources.getDimensionPixelSize(R.dimen.grid_spacing)

                // Hapus semua dekorasi yang ada
                val itemDecorations = recyclerView.itemDecorationCount
                for (i in 0 until itemDecorations) {
                    recyclerView.removeItemDecorationAt(0)
                }

                recyclerView.addItemDecoration(GridSpacingItemDecoration(spacing, true))
                makananAdapter.isListView = false
            }
        }

        makananAdapter.setOnItemClickCallback(object : MakananAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Makanan) {
                // Tambahkan kode untuk membuka detail makanan
                selectedMakanan(data)
            }
        })
    }

    private fun selectedMakanan(makanan: Makanan) {
        // Tambahkan kode untuk membuka detail makanan
        val bundle = Bundle()
        bundle.putString("nama", makanan.nama)
        bundle.putString("harga", makanan.harga)
        bundle.putInt("gambar", makanan.gambar)
        bundle.putString("deskripsi", makanan.deskripsi)
        bundle.putString("lokasi", makanan.lokasi)
        bundle.putString("linkLokasi", makanan.linkLokasi)
        view?.findNavController()?.navigate(R.id.action_homeFragment_to_detailMakananFragment, bundle)
    }

    private fun updateListButtonImage() {
        // Ubah gambar pada listButton berdasarkan status isListView
        val imageResource = if (isListView) R.drawable.baseline_list_24 else R.drawable.icons8_grid
        binding.listButton.setImageResource(imageResource)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}