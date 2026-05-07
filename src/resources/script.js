const unitsData = {
    length: ["CM", "METRE", "KM"],
    weight: ["TONNE", "GRAM", "KILOGRAM"],
    temperature: ["FAHRENHEIT", "CELSIUS", "KELVIN"]
};

// Função para mudar a categoria (Length, Weight, Temperature)
function setUnit(category) {
    // 1. Atualiza visual das abas
    document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));
    const activeTab = document.getElementById('tab-' + category);
    if (activeTab) activeTab.classList.add('active');

    // 2. Atualiza o valor que será enviado no JSON
    document.getElementById('unitInput').value = category;

    // 3. Atualiza o texto da label de entrada (opcional, para ficar igual ao mockup)
    document.getElementById('label-value').innerText = `Enter the ${category} to convert`;

    // 4. Limpa e preenche os selects 'from' e 'to'
    const fromSelect = document.getElementById('fromSelect');
    const toSelect = document.getElementById('toSelect');

    fromSelect.innerHTML = "";
    toSelect.innerHTML = "";

    unitsData[category].forEach(unit => {
        fromSelect.options.add(new Option(unit, unit));
        toSelect.options.add(new Option(unit, unit));
    });
}

// Inicializa a página com 'length'
setUnit('length');

// Manipulação do envio do formulário
const form = document.getElementById("converterForm");

form.addEventListener("submit", async (event) => {
    event.preventDefault();

    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    // IMPORTANTE: Converter o valor para número para o JSON ficar correto
    data.value = parseFloat(data.value);

    try {
        const response = await fetch('http://localhost:8001/conversions', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (!response.ok) throw new Error("Erro na resposta do servidor");

        const resultJson = await response.json();

        // Alterna para a tela de resultado
        showResult(data, resultJson.result);

    } catch (error) {
        alert("Erro: Não foi possível conectar à API. Verifique se o servidor Java está rodando e com o CORS configurado.");
        console.error(error);
    }
});

function showResult(requestData, resultValue) {
    document.getElementById('form-card').style.display = 'none';
    document.getElementById('result-container').style.display = 'block';

    // Atualiza a aba do resultado
    document.getElementById('res-tab-label').innerText = requestData.unit.charAt(0).toUpperCase() + requestData.unit.slice(1);

    // Formata a string do resultado: "100 CELSIUS = 212,00 FAHRENHEIT"
    document.getElementById('result-display').innerText =
        `${requestData.value} ${requestData.from} = ${resultValue} ${requestData.to}`;
}

function resetForm() {
    document.getElementById('form-card').style.display = 'block';
    document.getElementById('result-container').style.display = 'none';
    form.reset();
    setUnit('length'); // Volta ao padrão
}