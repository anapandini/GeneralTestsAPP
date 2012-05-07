package br.furb.portais.aplicacao.util;

import java.io.File;
import java.io.FileReader;
import java.io.ObjectInputStream;

import br.furb.portais.aplicacao.Ambiente;
import br.furb.portais.modelo.Divisao;
import br.furb.portais.modelo.Ponto;
import br.furb.portais.modelo.Sala;

import com.thoughtworks.xstream.XStream;

public class LeitorAmbientes {

	private static XStream xstream = new XStream();

	public static Ambiente readXmlFile(String xmlFileName) throws Exception {

		configXstream();

		File f = new File(xmlFileName);
		if (!f.exists())
			throw new Exception("Arquivo XML não encontrado ):");

		FileReader fr = new FileReader(f);
		ObjectInputStream in = xstream.createObjectInputStream(fr);

		Ambiente ambiente = (Ambiente) in.readObject();

		in.close();
		fr.close();

		return ambiente;
	}

	private static void configXstream() {

		xstream.alias("Ambiente", Ambiente.class);
		xstream.alias("Sala", Sala.class);
		xstream.alias("Divisao", Divisao.class);
		xstream.alias("pontoOrigem", Ponto.class);
		xstream.alias("pontoDestino", Ponto.class);

		xstream.aliasAttribute(Divisao.class, "origem", "pontoOrigem");
		xstream.aliasAttribute(Divisao.class, "destino", "pontoDestino");

		xstream.useAttributeFor(Sala.class, "identificadorSala");
		xstream.useAttributeFor(Sala.class, "identificadorSala");
		xstream.useAttributeFor(Divisao.class, "origem");
		xstream.useAttributeFor(Divisao.class, "destino");
		xstream.useAttributeFor(Divisao.class, "tipo");
		xstream.useAttributeFor(Ponto.class, "x");
		xstream.useAttributeFor(Ponto.class, "y");
		xstream.useAttributeFor(Ponto.class, "sala");

		xstream.addImplicitCollection(Ambiente.class, "salas");
		xstream.addImplicitCollection(Sala.class, "divisoes");
	}
}
