//edo
function initPage() {
	$
			.getJSON(
					"/secondapp/rest/countries",
					function(json) {
						$
								.each(
										json,
										function(key, item) {
											$('#countryTable')
													.append(
															'<tr id="'
																	+ item.code
																	+ '"><td>'
																	+ item.code
																	+ '</td>'
																	+ '<td class="capital" onclick="selectCountry('
																	+ item.code
																	+ ')">'
																	+ item.capital
																	+ '</td><td>'
																	+ item.region
																	+ '</td><td>'
																	+ item.surface
																	+ '</td><td>'
																	+ item.peasants
																	+ '</td><td><button onclick="deleteCountry('
																	+ item.code
																	+ ')">verwijderen</button></td>'
																	+ '</tr>');
										});
					});
}

function selectCountry(land) {
	var id = land.id;
	$.getJSON("/secondapp/rest/countries/" + id, function(json) {
		$.each(json, function(key, item) {
			$("#iso").html(item.code)
			$("#land").val(item.name)
			$("#hoofdstad").val(item.capital)
			$("#regio").val(item.region)
			$("#oppervlakte").val(item.surface)
			$("#inwoners").val(item.peasants)
		});
	});
}

function deleteCountry(derp){
	var id = derp.id;
	console.log(id);
    var uri = "/secondapp/rest/countries/" + id;
    
    $.ajax(uri, { 
        type: "delete", 
        success: function(response) {
            $("#response").text("Country deleted!");
        },
        error: function(response) {
            $("#response").text("Could not delete country!");
        }
    });
};


function find() {
	var input, filter, table, tr, td, i;
	input = document.getElementById("myInput");
	filter = input.value.toUpperCase();
	table = document.getElementById("table");
	tr = table.getElementsByTagName("tr");
	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[2];
		if (td) {
			if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
				tr[i].style.display = "";
			} else {
				tr[i].style.display = "none";
			}
		}
	}
}

function createCountry(){
	var data = $("#newCountryForm").serialize();
	$.post("/secondapp/rest/countries", data, function(response) {
		$("#response")
		$("#response").text(JSON.stringify(response));
	});
}
function updateCountry(){
	var ID = $("#iso").text();
    var uri = "/secondapp/rest/countries/" + ID;
    
    $.ajax(uri, { 
        type: "put", 
        data: $("#updateCustomerForm").serialize(),
        success: function(response) {
            $("#response").text("Country updated!");
        },
        error: function(response) {
            $("#response").text("Could not update country!");
        }
    });
};


