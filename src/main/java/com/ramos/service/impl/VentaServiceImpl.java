package com.ramos.service.impl;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.ramos.dto.VentaResumenDTO;
import com.ramos.model.Venta;
import com.ramos.repo.IGenericRepo;
import com.ramos.repo.IVentaRepo;
import com.ramos.service.IVentaService;

import javax.transaction.Transactional;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class VentaServiceImpl extends CRUDImpl<Venta, Integer> implements IVentaService {

	@Autowired
	private IVentaRepo repo;

	@Override
	protected IGenericRepo<Venta, Integer> getRepo() {
		return repo;
	}

	@Transactional
	@Override
	public Venta registrarTransaccional(Venta venta) throws Exception {

		venta.getDetalleVenta().forEach(det -> det.setVenta(venta));
		return repo.save(venta);
	}

	@Override
	public List<Venta> buscar(String dni, String nombreCompleto) {
		// TODO Auto-generated method stub
		return repo.buscar(dni, nombreCompleto);
	}

	@Override
	public List<Venta> buscarFecha(LocalDateTime fecha1, LocalDateTime fecha2) {
		// TODO Auto-generated method stub
		return repo.buscarFecha(fecha1, fecha2.plusDays(1));
	}

	@Override
	public List<VentaResumenDTO> listarResumen() {

		// lista de arreglo de objetos List<Objet[]>
		List<VentaResumenDTO> ventas = new ArrayList<>();

		repo.listarResumen().forEach(x -> {
			VentaResumenDTO vr = new VentaResumenDTO();
			vr.setCantidad(Integer.parseInt(String.valueOf(x[0])));
			vr.setFecha(String.valueOf(x[1]));
			ventas.add(vr);
		});

		return ventas;
	}

	@Override
	public byte[] generarReporte() {

		byte[] data = null;

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("txt_titulo", "Prueba de titulo");

		File file;
		try {
			file = new ClassPathResource("/reports/ventas.jasper").getFile();
			JasperPrint print = JasperFillManager.fillReport(file.getPath(), parametros, new JRBeanCollectionDataSource(listarResumen()));
			data = JasperExportManager.exportReportToPdf(print);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;

	}

}
