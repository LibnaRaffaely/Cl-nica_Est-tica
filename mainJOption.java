import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.clinicaestetica.Models.Cliente;
import com.clinicaestetica.Models.Clinica;
import com.clinicaestetica.Models.Pacote;
import com.clinicaestetica.Models.Profissional;
import com.clinicaestetica.Models.Sessao;
import com.clinicaestetica.Models.Enums.Pagamento;
import com.clinicaestetica.Models.Enums.Status;
import com.clinicaestetica.Models.Enums.TipoPacote;
import com.clinicaestetica.Repositories.ClienteRepository;
import com.clinicaestetica.Repositories.ClinicaRepository;
import com.clinicaestetica.Repositories.ProfissionalRepository;
import com.clinicaestetica.Repositories.SessaoRepository;

public class mainJOption {

	public static void main(String[] args) {
		Object[] generos = {"Feminino", "Masculino", "Não Binario"};
		
		//Pag principal
		JFrame mainFrame = new JFrame("Clinica Estética");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(500,300);
		mainFrame.setLayout(new FlowLayout());
		
		//painel auxiliar
		JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints maingbc = new GridBagConstraints();
        maingbc.insets = new Insets(10, 10, 10, 10); 
          
		//Botão
		JButton b1 = new JButton("Cadastrar Sessão");
		JButton b2 = new JButton("Cadastrar Pacote");
		JButton b3 = new JButton("Cadastrar Cliente");
		JButton b4 = new JButton("Cadastrar Profissional");
		JButton b5 = new JButton("Cadastrar Clínica");
		JButton b6 = new JButton("Atualizar Sessão");
		JButton b7 = new JButton("Contab. Profissional");
		JButton b8 = new JButton("Contab. Clínica");
		JButton b9 = new JButton("Contab. Cliente");
		Dimension buttonSize = new Dimension(180, 30); 
        b1.setPreferredSize(buttonSize);
        b2.setPreferredSize(buttonSize);
        b3.setPreferredSize(buttonSize);
        b4.setPreferredSize(buttonSize);
        b5.setPreferredSize(buttonSize);
        b6.setPreferredSize(buttonSize);
        b7.setPreferredSize(buttonSize);
        b8.setPreferredSize(buttonSize);
        b9.setPreferredSize(buttonSize);
		
		b1.addActionListener(e -> {
			
			//painel e layout
			JPanel panel = new JPanel();
	        panel.setLayout(new GridBagLayout());
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(5, 5, 5, 5); 
	        gbc.anchor = GridBagConstraints.WEST; 
	        gbc.fill = GridBagConstraints.HORIZONTAL; 
	        gbc.weightx = 1; 
	        
	        // cadastro data e horario
	        JLabel dataS = new JLabel("data [dd/mm/aaaa]");
	        gbc.gridx = 0;
	        gbc.gridy = 0;
	        panel.add(dataS, gbc);
	        JTextField dataSF = new JTextField(15);
	        gbc.gridx = 1;
	        panel.add(dataSF, gbc);
	        
	        JLabel tempoS = new JLabel("Hora (HH:mm):");
	        gbc.gridx = 0;
	        gbc.gridy = 1;
	        panel.add(tempoS, gbc);

	        JTextField tempoSF = new JTextField(5);
	        gbc.gridx = 1;
	        panel.add(tempoSF, gbc);
	         
	       
	        //pagamento
	        JLabel pagS = new JLabel("Pagamento: ");
	        gbc.gridx = 0;
	        gbc.gridy = 3;
	        panel.add(pagS, gbc);

	        JComboBox<String> pagSF = new JComboBox<>(new String[] {"EFETUADO","PENDENTE","SEM_COBRANCA"});
	        gbc.gridx = 1;
	        gbc.gridy = 3;
	        panel.add(pagSF, gbc);
	        
	        //profissional
	        JLabel profS = new JLabel("Id Profissional: ");
	        gbc.gridx = 0;
	        gbc.gridy = 4;
	        panel.add(profS, gbc);
	        JTextField profSF = new JTextField(15);
	        gbc.gridx = 1;
	        panel.add(profSF, gbc);
	        
	        
	      //Cliente
	        JLabel cliS = new JLabel("Id Cliente: ");
	        gbc.gridx = 0;
	        gbc.gridy = 5;
	        panel.add(cliS, gbc);
	        JTextField cliSF = new JTextField(15);
	        gbc.gridx = 1;
	        panel.add(cliSF, gbc);
	        
	      //Clinica
	        JLabel clinS = new JLabel("Id Clinica: ");
	        gbc.gridx = 0;
	        gbc.gridy = 6;
	        panel.add(clinS, gbc);
	        JTextField clinSF = new JTextField(15);
	        gbc.gridx = 1;
	        panel.add(clinSF, gbc);
	        
	        int result = JOptionPane.showConfirmDialog(mainFrame, panel, "Cadastro de Usuário", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        
	        
	        if(result == JOptionPane.OK_OPTION) {
		        //data
	        	String tempoString = tempoSF.getText().trim();
	        	String dataString = dataSF.getText().trim();
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		        LocalDateTime dataHora = LocalDateTime.parse(dataString + ' ' + tempoString, formatter);
		        //pagamento
		        int indPag = pagSF.getSelectedIndex();
		        //profissional
		        ProfissionalRepository profRepo = new ProfissionalRepository();
		        int idProf = Integer.parseInt(profSF.getText());
		        Profissional auxProf = profRepo.obterPorId(idProf);
		        //cliente
		        ClienteRepository clienteRepo = new ClienteRepository();
		        int idCli = Integer.parseInt(cliSF.getText());
		        Cliente auxCli = clienteRepo.obterPorId(idCli);
		        //clinica
	
		        ClinicaRepository clinicaRepo = new ClinicaRepository();
		        int idClin = Integer.parseInt(clinSF.getText());
		        Clinica auxClin = clinicaRepo.obterPorId(idClin);
		        
		        Sessao sessao = new Sessao(dataHora, Pagamento.values()[indPag],auxProf, auxCli, auxClin);
		        SessaoRepository sessaoRepo = new SessaoRepository();
		        
		        if(!auxCli.getPacotes().get(auxCli.getPacotes().size() -1).pacoteDisponivel()){
		        	auxCli.addSessaoPacote(sessao);
		        	sessaoRepo.registrar(sessao);
		        	ClienteRepository cliRepo = new ClienteRepository();
			        cliRepo.editar(auxCli);
			        JOptionPane.showMessageDialog(mainFrame, sessao.getId() + "  |  " + sessao.getData() + "\n" + sessao.getProfissional() + "\n" + sessao.getCliente().getNomeCompleto());
		        } else {
		        	JOptionPane.showMessageDialog(mainFrame,"Pacote cheio!");
		        }
		        
	        }else {
	        	JOptionPane.showMessageDialog(mainFrame, "Cadastro cancelado.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
	        }
	        
		});
		
		maingbc.gridx = 0;
		maingbc.gridy = 0;
        mainPanel.add(b1, maingbc);
		
		b2.addActionListener(e -> {
			int tipoPacote = JOptionPane.showOptionDialog(mainFrame, "Qual o Tipo de Pacote desejado?", null, JOptionPane.DEFAULT_OPTION,  JOptionPane.INFORMATION_MESSAGE, null, TipoPacote.values(), TipoPacote.UNICO);
			Pacote pacote = null;
			switch(tipoPacote) {
				case 0:
					 pacote = new Pacote(TipoPacote.COMUM);
					break;
				case 1:
					 pacote = new Pacote(TipoPacote.VIP);
					break;
				case 2:
					 pacote = new Pacote(TipoPacote.PREMIUM);
					break;
				case 3:
					 pacote = new Pacote(TipoPacote.UNICO);
					break;
			}
			String idCliente = JOptionPane.showInputDialog("Informe o id do Cliente:");
			int idCliInt = Integer.parseInt(idCliente);
			ClienteRepository cliRepo = new ClienteRepository();
			Cliente auxCli = cliRepo.obterPorId(idCliInt);
			auxCli.addPacote(pacote);
			cliRepo.editar(auxCli);
			//System.out.println(auxCli.toString());
			
			JOptionPane.showMessageDialog(mainFrame, "Tipo: " + pacote.getTipoPacote() + "  -  "+ pacote.getId() +"\nSessões: " + pacote.getqntd() + "\nValor Unitário: " + pacote.getValorUnitario());
			
		});
		
		maingbc.gridx = 1;
		maingbc.gridy = 0;
		mainPanel.add(b2, maingbc);
		
		
		b3.addActionListener(e -> {
			String nomeC = JOptionPane.showInputDialog(mainFrame,"Informe o nome desse Cliente:");
			String cpfC = JOptionPane.showInputDialog(mainFrame,"Informe o CPF desse Cliente: ");
			String contatoC = JOptionPane.showInputDialog(mainFrame,"Informe o contato desse Cliente: ");
			Object generoC = JOptionPane.showInputDialog(mainFrame, "Escolha o gênero: ", "Gênero Cliente", JOptionPane.INFORMATION_MESSAGE,null,generos,generos[0]);
			char generoCC = 0;
			switch(generoC.toString()) {
				case "Feminino":
					generoCC  = 'F';
					break;
				case "Masculino":
					generoCC = 'M';
					break;
				case "Não Binario":
					generoCC = 'N';
					break;
			}
			Cliente cliente = new Cliente(nomeC, cpfC, contatoC, generoCC);
			JOptionPane.showMessageDialog(mainFrame, " Id: "+ cliente.getId() + "\n" + cliente.getNomeCompleto() + " - "+ cliente.getGenero() + "\n CPF:" + cliente.getCpf() + "\n Contato:" + cliente.getContato(), "Cliente Cadastrado!", JOptionPane.PLAIN_MESSAGE );
			ClienteRepository cliRepo = new ClienteRepository();
			cliRepo.registrar(cliente);
			
		});
		
		maingbc.gridx = 0;
		maingbc.gridy = 1;
		mainPanel.add(b3, maingbc);
        
		b4.addActionListener(e -> {
			String nomeP = JOptionPane.showInputDialog(mainFrame,"Informe o nome desse profissional: ");
			String cpfP = JOptionPane.showInputDialog(mainFrame,"Informe o CPF desse profissional: ");
			String contatoP = JOptionPane.showInputDialog(mainFrame,"Informe o contato desse profissional: ");
			Object generoP = JOptionPane.showInputDialog(mainFrame, "Escolha o gênero: ", "Gênero Profissional", JOptionPane.INFORMATION_MESSAGE,null,generos,generos[0]);
			char generoPC = 0;
			switch(generoP.toString()) {
				case "Feminino":
					generoPC  = 'F';
					break;
				case "Masculino":
					generoPC = 'M';
					break;
				case "Não Binario":
					generoPC = 'N';
					break;
				}
			Profissional profissonal = new Profissional(nomeP, cpfP, contatoP, generoPC);
			JOptionPane.showMessageDialog(mainFrame,"Cadastrado!\nId: " + profissonal.getId() +"\n"+ profissonal.toString());
			ProfissionalRepository profRepo = new ProfissionalRepository();
			profRepo.registrar(profissonal);
			
		});
		
		maingbc.gridx = 1;
		maingbc.gridy = 1;
		mainPanel.add(b4, maingbc);
        
		b5.addActionListener(e -> {
			String endereco = JOptionPane.showInputDialog(mainFrame,"Informe o endereço da Clínica: ");
			Clinica clinica = new Clinica(endereco);
			JOptionPane.showMessageDialog(mainFrame,"Clínica Cadastrada" + "\n Endereço: " + clinica.getEndereco() + "\n Id: " + clinica.getId());
			ClinicaRepository clinicaRepo = new ClinicaRepository();
			clinicaRepo.registrar(clinica);
		});
		
		maingbc.gridx = 0;
		maingbc.gridy = 2;
		mainPanel.add(b5, maingbc);
        
		b6.addActionListener(e -> {
			
			String resp = JOptionPane.showInputDialog(mainFrame, "Insira o id do cliente: ");
			int respInt = Integer.parseInt(resp);
			//painel para alteração
			JPanel Sespanel = new JPanel();
			Sespanel.setLayout(new GridBagLayout());
	        GridBagConstraints Sesgbc = new GridBagConstraints();
	        Sesgbc.insets = new Insets(5, 5, 5, 5); 
	        Sesgbc.anchor = GridBagConstraints.WEST; 
	        Sesgbc.fill = GridBagConstraints.HORIZONTAL; 
	        Sesgbc.weightx = 1; 
	        
	        //Cliente
	        JComboBox<String> sesAgendF = new JComboBox<>();
	        List<Integer> ids = new ArrayList<>();
	        ClienteRepository auxCliSes = new ClienteRepository();
	        Cliente clienteSes = auxCliSes.obterPorId(respInt);
	        Pacote pacote = clienteSes.getPacotes().get(clienteSes.getPacotes().size() - 1);
	        for(Sessao s: pacote.getSessoes()) {
	        	if(s.getStatus() == Status.AGENDADO) {
	        		sesAgendF.addItem("Id: " + s.getId() + " | " + s.getData());
	        		ids.add(s.getId());
	        	}
	        }
	        JLabel sesAgend = new JLabel("Sessão: ");
	        Sesgbc.gridx = 0;
	        Sesgbc.gridy = 0;
	        Sespanel.add(sesAgend, Sesgbc);
	        
	        Sesgbc.gridx = 1;
	        Sesgbc.gridy = 0;
	        Sespanel.add(sesAgendF, Sesgbc);
	        
	      //pagamento atualizar
	        JLabel pagSes = new JLabel("Pagamento: ");
	        Sesgbc.gridx = 0;
	        Sesgbc.gridy = 3;
	        Sespanel.add(pagSes, Sesgbc);

	        JComboBox<String> pagSesF = new JComboBox<>(new String[] {"EFETUADO","PENDENTE","SEM_COBRANCA"});
	        Sesgbc.gridx = 1;
	        Sesgbc.gridy = 3;
	        Sespanel.add(pagSesF, Sesgbc);
	        
	      //Status atualizar
	        JLabel statSes = new JLabel("Status: ");
	        Sesgbc.gridx = 0;
	        Sesgbc.gridy = 4;
	        Sespanel.add(statSes, Sesgbc);

	        JComboBox<String> statSF = new JComboBox<>(new String[] {"CONCLUIDO","REMARCADO","CANCELADO"});
	        Sesgbc.gridx = 1;
	        Sesgbc.gridy = 4;
	        Sespanel.add(statSF, Sesgbc);
	        
	      
	        
	        int Sesresult = JOptionPane.showConfirmDialog(mainFrame, Sespanel, "Cadastro de Usuário", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        if(Sesresult == JOptionPane.OK_OPTION) {
	        	int indPagSes = pagSesF.getSelectedIndex();
	        	int indStatSes = statSF.getSelectedIndex();
	        	int indSes = sesAgendF.getSelectedIndex();
	        	LocalDateTime dataHoraAtualiz = null;
	        		if(indStatSes == 1) {
	    	        	String dataSt = JOptionPane.showInputDialog(mainFrame,"Informe a data:");
	    	        	String tempoSt = JOptionPane.showInputDialog(mainFrame,"Informe o horário:");
	    		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	    		        dataHoraAtualiz = LocalDateTime.parse(dataSt + ' ' + tempoSt, formatter);
	        		}
	        	int auxIndice = ids.get(indSes);
	        	SessaoRepository auxSes = new SessaoRepository();
	        	Sessao s1 = auxSes.obterPorId(auxIndice);
	        	
	        	s1.alterarPagamento(Pagamento.values()[indPagSes]);
	        	s1.alterarStatus(Status.values()[indStatSes], dataHoraAtualiz);
	        	auxSes.editar(s1);
	        	
	        	int i = 0;
	        	for(Sessao s: pacote.getSessoes()) {
	        		if(s.getId() != auxIndice) {
	        			i++;
	        		}
	        		else {break; }
	        		
	        	}
	        	
	        	
	        		clienteSes.getPacotes().get(clienteSes.getPacotes().size() - 1 ).getSessoes().get(i).alterarPagamento(Pagamento.values()[indPagSes]);
		        	clienteSes.getPacotes().get(clienteSes.getPacotes().size() - 1 ).getSessoes().get(i).alterarStatus(Status.values()[indStatSes], dataHoraAtualiz);
		        	ClienteRepository auxCliSes2 = new ClienteRepository();
		        	auxCliSes2.editar(clienteSes);
	        	
	        	
	        	
	        	
	        	ProfissionalRepository profRepo = new ProfissionalRepository();
	    		Profissional p1 = clienteSes.getPacotes().get(clienteSes.getPacotes().size() - 1 ).getSessoes().get(i).getProfissional();
	    		profRepo.editar(p1);
	    		
	    		ClinicaRepository clinRepo = new ClinicaRepository();
	    		Clinica clin1 = clienteSes.getPacotes().get(clienteSes.getPacotes().size() - 1 ).getSessoes().get(i).getClinica();
	    		clinRepo.editar(clin1);
	        	
	        	JOptionPane.showMessageDialog(mainFrame, s1.getId() + "  |  " + s1.getData() + "\nStatus " + s1.getStatus() + "\nPagamento " + s1.getPagamento()+ "\n" +s1.getProfissional() + "\n" + s1.getCliente().getNomeCompleto());
		        
	        	
	        }else {
	        	JOptionPane.showMessageDialog(mainFrame, "Atualização cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
	        }
			
		});
		
		maingbc.gridx = 1;
		maingbc.gridy = 2;
		mainPanel.add(b6, maingbc);
		
		b7.addActionListener(e -> {
			String respProf = JOptionPane.showInputDialog(mainFrame,"Insira o id do Profissional:");
			int idRespProf = Integer.parseInt(respProf);
			ProfissionalRepository profRepoCont = new ProfissionalRepository();
			Profissional profCont = profRepoCont.obterPorId(idRespProf);
			JOptionPane.showMessageDialog(mainFrame,"id: " + profCont.getId() + "\n" + profCont.getNomeCompleto() + "\n" + profCont.getContato() + "   |   " + profCont.getGenero() + "\nContabilidade dos Atendimentos: " + profCont.getContabAtendimentos());
			
		});
		
		maingbc.gridx = 0;
		maingbc.gridy = 3;
		mainPanel.add(b7, maingbc);
		
		b8.addActionListener(e -> {
			String respClinica = JOptionPane.showInputDialog(mainFrame,"Insira o id da Clínica:");
			int idRespClinica = Integer.parseInt(respClinica);
			ClinicaRepository clinicaRepoCont = new ClinicaRepository();
			Clinica clinicaCont = clinicaRepoCont.obterPorId(idRespClinica);
			JOptionPane.showMessageDialog(mainFrame,"id: " + clinicaCont.getId() + "\n" + clinicaCont.getEndereco() + "\n" + "\nContabilidade das Sessões: " + clinicaCont.getcontabilidadeClinica());
			
		});
		
		maingbc.gridx = 1;
		maingbc.gridy = 3;
		mainPanel.add(b8, maingbc);
		
		b9.addActionListener(e -> {
			String respCliente = JOptionPane.showInputDialog(mainFrame,"Insira o id do Cliente:");
			int idRespCliente = Integer.parseInt(respCliente);
			ClienteRepository clienteRepoCont = new ClienteRepository();
			Cliente clienteCont = clienteRepoCont.obterPorId(idRespCliente);
			JOptionPane.showMessageDialog(mainFrame,"id: " + clienteCont.getId() + "\n" + clienteCont.getNomeCompleto() + "\n" + clienteCont.getContato() + "   |   " + clienteCont.getGenero() + "\nContabilidade das Sessões: " + clienteCont.getContabSessoes());
			
		});
		
		maingbc.gridx = 0;
		maingbc.gridy = 4;
		mainPanel.add(b9, maingbc);
		
        mainFrame.add(mainPanel);
        mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		
		
		
	}

}
