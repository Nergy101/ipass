initPage2();

function dConfirmatie(afspraaknummer) {// om de wijziging een 2e confirmatie nodig te laten
	// hebben

	if (confirm("Weet u zeker dat u dit wilt doen?") == true) {
		deleteAfspraak(afspraaknummer);
	} else {
		$("#uResponse").empty();
		$("#uResponse").text("U heeft gekozen de afspraak niet te verwijderen.");
	}
}

function wConfirmatie() {// om de wijziging een 2e confirmatie nodig te laten
	// hebben

	if (confirm("Weet u zeker dat u dit wilt doen?") == true) {
		updateAfspraak();
	} else {
		$("#uResponse").empty();
		$("#uResponse").text("U heeft gekozen de afspraak niet te wijzigen.");
	}
}

function initPage2() { // Checkt voor veranderingen aan het datum field
	$('#datum[type="date"]').change(function() {

		d = new Date(this.value);
		var curr_date = ("0" + d.getDate()).slice(-2)
		var curr_month = ("0" + (d.getMonth() + 1)).slice(-2); // Maand begint
		// bij 0
		var curr_year = d.getFullYear();
		dS = curr_year + "-" + curr_month + "-" + curr_date;
	});
};

function initPage() {// Vult de tabel met alle afspraken

	$.getJSON("/rest/afspraken", function(json) {
		$.each(json, function(key, item) {
			$('#afspraakTable').append(
					'<tr id="' + item.afspraaknummer + '"><td>'
							+ item.afspraaknummer + '</td><td>'
							+ item.werknemersnummer + '</td><td>'
							+ item.achternaam + ", " + item.voornaam
							+ '</td><td>' + item.telefoonnummer + '</td><td>'
							+ item.email_adres + '</td><td>' + item.begintijd
							+ '</td><td>' + item.datum
							+ '</td><td><button onclick="dConfirmatie('
							+ item.afspraaknummer + ')">Verwijderen</button>'
							+ '</td><td><button onclick="selectAfspraak('
							+ item.afspraaknummer
							+ ')">Wijzigen</button></td></tr>');
		});
	});
}

var tijden = [ '09:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00',
		'16:00', '17:00' ] // mogelijke werktijden

function checkDate2() { // checkt op veranderingen aan de datum
	$('#uDatum[type="date"]').change(function() {
		var selectedText = document.getElementById('uDatum').value;
		var selectedDate = new Date(selectedText);
		var now = new Date();
		if (selectedDate < now) {
			$("#uTijdTable").empty();
			$("#uTijden").empty();
			$("#uResponse").empty();
			$("#tijdTable").empty();
			$("#update").prop("disabled", true);
			$('#uResponse').css('text-decoration', 'underline', 'red');
			$('#uResponse').css('color', 'red');
			$("#uResponse").append("Datum moet in de toekomst liggen!");

		} else {
			geefTijden2();
			lGeefTijden2();
			$("#uResponse").empty();
			if ($('#uTijden').is(':empty')) {
				$("#update").prop("disabled", false);
				$('#uResponse').css('background-color', 'white');
				$('#uResponse').css('text-decoration', 'none');
				$('#uResponse').css('color', 'black');
				$("#uTijden").append('Deze tijden zijn beschikbaar:');
			}
		}
	});
}

function checkDate() { // checkt op veranderingen aan de datum
	$('#datum[type="date"]').change(function() {
		var selectedText = document.getElementById('datum').value;
		var selectedDate = new Date(selectedText);
		var now = new Date();
		if (selectedDate < now) {
			$("#tijdTable").empty();
			$("#tijden").empty();
			$("#lTijdTable").empty();
			$("#response").empty();
			$("#post").prop("disabled", true);
			$('#response').css('text-decoration', 'underline', 'red');
			$('#response').css('color', 'red');
			$("#response").append("Datum moet in de toekomst liggen!");
		} else {
			$("#response").empty();
			if ($('#tijden').is(':empty')) {
				$("#post").prop("disabled", false);
				$('#response').css('background-color', 'white');
				$('#response').css('text-decoration', 'none');
				$('#response').css('color', 'black');
				$("#tijden").append('Deze tijden zijn beschikbaar:');
			}
			geefTijden();
			// location.reload();
			lGeefTijden();
		}
	});
}

function lGeefTijden() { // Kijk of tijdTable leeg is en append de tijdlijst
	$("#lTijdTable").empty();
	if ($('#tijdTable').children().length == 0) {

		for (var i = 0, tot = tijden.length; i < tot; i++) {
			if ($('\\#' + tijden[i]).length) {
				// object already exists
			} else {
				$('#lTijdTable').append(
						'<table><tr id="' + tijden[i] + '">'
								+ '</td><td class="tijden" onclick="getTijd('
								+ "'" + tijden[i] + "'" + ')">' + tijden[i]
								+ '</td></tr></table>');
			}

		}
	}
}

function lGeefTijden2() { // Kijk of tijdTable leeg is en append de tijdlijst
	$("#lTijdTable").empty();
	if ($('#uTijdTable').children().length == 0) {

		for (var i = 0, tot = tijden.length; i < tot; i++) {
			if ($('\\#' + tijden[i]).is(':empty')) {
				// object already exists
			} else {
				$('#lTijdTable').append(
						'<table><tr id="' + tijden[i] + '">'
								+ '</td><td class="tijden" onclick="getTijd('
								+ "'" + tijden[i] + "'" + ')">' + tijden[i]
								+ '</td></tr></table>');
			}

		}
	}
}

function geefTijden() {// append alle niet meer beschikbare tijden aan
						// tijdTable
	$("#tijdTable").empty();
	$
			.getJSON(
					"/rest/afspraken",
					function(json) {
						$
								.each(
										json,
										function(key, item) {
											if (dS.includes(item.datum)) {
												// $("#tijdTable").empty();
												for (var i = 0, tot = tijden.length; i < tot; i++) {
													if (tijden[i]
															.includes(item.begintijd)) {
														$('#tijdTable')
																.append(
																		'<table><tr id="'
																				+ tijden[i]
																				+ '">'
																				+ '</td><td class="tijden" onclick="getTijd('
																				+ "'"
																				+ null
																				+ "'"
																				+ ')">'
																				+ tijden[i]
																				+ ' niet beschikbaar'
																				+ '</td></tr></table>');
													}
												}
											}
										})
					})
};

function geefTijden2() { // append alle niet meer beschikbare tijden aan
							// tijdTable
	$("#uTijdTable").empty();
	var d = new Date($("#uDatum").val());
	var curr_date = ("0" + d.getDate()).slice(-2)
	var curr_month = ("0" + (d.getMonth() + 1)).slice(-2); // Maand begint
	// bij 0
	var curr_year = d.getFullYear();
	dS = curr_year + "-" + curr_month + "-" + curr_date;
	$
			.getJSON(
					"/rest/afspraken",
					function(json) {
						$
								.each(
										json,
										function(key, item) {

											if (dS.includes(item.datum)) {
												// $("#tijdTable").empty();
												for (var i = 0, tot = tijden.length; i < tot; i++) {
													if (tijden[i]
															.includes(item.begintijd)) {
														$('#uTijdTable')
																.append(
																		'<table><tr id="'
																				+ tijden[i]
																				+ '">'
																				+ '</td><td class="tijden" onclick="getTijd('
																				+ "'"
																				+ null
																				+ "'"
																				+ ')">'
																				+ tijden[i]
																				+ ' niet beschikbaar'
																				+ '</td></tr></table>');
													}
												}
											}

										})
					})
};

function getTijd(tijd) { // vul de geselecteerde tijd in het tijdvak
	$("#begintijd").val(tijd);
}

function maakAfspraak() { // creëer een afspraak in de database
	var data = $("#newAfspraakForm").serialize();
	if (data.includes('=&')) { // als de data een nullwaarde bevat doe dit:
		$('#response').empty();
		$('#response').append(
				'Er is iets fout gegaan, is alles wel (correct) ingevuld?');

	} else { // als de aanvraag geen nullwaardes bevat
		$.post("/rest/afspraken", data, function(response) {
		})
	}
};

function deleteAfspraak(a) { // delete een afspraak uit de database
	var uri = "/rest/afspraken/" + a;

	$.ajax(uri, {
		type : "delete",
		success : function(response) {
			$("#response").text("Afspraak verwijderd!");
		},
		error : function(response) {
			$("#response").text("Afspraak verwijderen mislukt!");
		}
	});
};

function updateAfspraak() { // update een afspraak
	var aNr = $("#uAfspraaknummer").val();
	var uri = "/rest/afspraken/" + aNr;

	$.ajax(uri, {
		type : "put",
		data : $("#updateAfsprakenForm").serialize(),
		success : function(response) {
			$("#uResponse").text("Afspraak gewijzigd!");
		},
		error : function(response) {
			$("#uResponse").text(
					"Er ging iets fout, de afspraak is niet gewijzigd!");
		}
	});
};

function selectAfspraak(afspraakNummer) { // stop een afspraak in het
											// 'update'-formulier
	$.getJSON("/rest/afspraken/" + afspraakNummer, function(json) {
		$("#uAfspraaknummer").val(json.afspraaknummer)
		$("#uWerknemersnummer").val(json.werknemersnummer)
		$("#uVoornaam").val(json.voornaam)
		$("#uAchternaam").val(json.achternaam)
		$("#uTelefoonnummer").val(json.telefoonnummer)
		$("#uE-mail_adres").val(json.email_adres)
		$("#begintijd").val(json.begintijd)
		$("#uDatum").val(json.datum)
		// enable inputvakken
		$("#uWerknemersnummer").prop("disabled", false);
		$("#uVoornaam").prop("disabled", false);
		$("#uAchternaam").prop("disabled", false);
		$("#uTelefoonnummer").prop("disabled", false);
		$("#uE-mail_adres").prop("disabled", false);
		$("#begintijd").prop("disabled", false);
		$("#uDatum").prop("disabled", false);
	});

}

function wwCheck() { // check ww
	if ($("#wachtwoord").val() == '1002') {
		if ($("#werknemersnummer").val() in [ 1, 2, 3 ]) {
			window.location.assign("werknemer.html")
		}

	}
}

function findNummer() { // zoekactie waar wordt gezocht op afspraaknummer
	var input, filter, table, tr, td, i;
	input = document.getElementById("myInputNummer");
	filter = input.value.toUpperCase();
	table = document.getElementById("afspraakTable");
	tr = table.getElementsByTagName("tr");
	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[0]; // <-- column 0 ==
													// afspraaknummer
		if (td) {
			if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
				tr[i].style.display = "";
			} else {
				tr[i].style.display = "none";
			}
		}
	}
}

function findNaam() { // zoekactie waar wordt gezocht op naam
	var input, filter, table, tr, td, i;
	input = document.getElementById("myInputNaam");
	filter = input.value.toUpperCase();
	table = document.getElementById("afspraakTable");
	tr = table.getElementsByTagName("tr");
	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[2]; // <-- column 2 == naam
		if (td) {
			if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
				tr[i].style.display = "";
			} else {
				tr[i].style.display = "none";
			}
		}
	}
}

function findDate() { // zoekactie waar wordt gezocht op datum
	var input, filter, table, tr, td, i;
	input = document.getElementById("myInputDate");
	filter = input.value.toUpperCase();
	table = document.getElementById("afspraakTable");
	tr = table.getElementsByTagName("tr");
	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[6]; // <-- column 6 == datum
		if (td) {
			if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
				tr[i].style.display = "";
			} else {
				tr[i].style.display = "none";
			}
		}
	}
}