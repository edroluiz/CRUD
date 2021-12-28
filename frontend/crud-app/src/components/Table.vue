<template>
  <div style="margin: 0 auto; width: 100%">
    <Menubar class="menu" :model="menu" />
    <Panel
      header="Olá Professor(a), aqui você tem a liberdade de editar e excluir as suas informações."
    />
    <Menubar class="itens" :model="itemsTeacher" />
    <br />
    <DataTable
      :value="personas"
      :paginator="true"
      :rows="10"
      :selection.sync="selectedTeacher"
      dataKey="id"
    >
      <div>
        <transition-group name="p-message" tag="div">
          <Message
            v-for="msg of messages"
            :severity="msg.severity"
            :life="3000"
            :sticky="false"
            :key="msg.id"
            >{{ msg.content }}</Message
          >
        </transition-group>
      </div>
      <Column selectionMode="single" headerStyle="width: 3em"></Column>
      <Column field="id" header="ID"></Column>
      <Column field="name" header="Professor(a)"></Column>
      <Column field="registration" header="Matrícula"></Column>
      <Column field="disciplines" header="Nome da Disciplina">
        <template #body="{ data }">
          <span v-for="(discipline, index) in data.disciplines" :key="index">
            {{ discipline.disciplineName }}
          </span>
        </template>
      </Column>
      <Column field="disciplines" header="Tipo de Ensino">
        <template #body="{ data }">
          <span v-for="(discipline, index) in data.disciplines" :key="index">
            {{ discipline.type_of_teaching }}
          </span>
        </template>
      </Column>
      <Column field="disciplines" header="Carga Horária">
        <template #body="{ data }">
          <span v-for="(discipline, index) in data.disciplines" :key="index">
            {{ discipline.workload + " Horas" }}
          </span>
        </template>
      </Column>
    </DataTable>

    <Dialog :visible.sync="displayModal" :modal="true">
      <h2>Errou alguma coisa? Não se preocupe...</h2>
      <p>Você pode editar qualquer informação abaixo:</p>
      <br />
      <span class="p-float-label">
        <InputText
          id="name"
          type="text"
          v-model="TeacherDTO.name"
          style="width: 100%"
        />
        <label for="name"> Nome Completo </label>
      </span>
      <br />
      <span class="p-float-label">
        <InputText
          id="registration"
          type="text"
          v-model="TeacherDTO.registration"
          style="width: 100%"
        />
        <label for="registration"> Matrícula </label>
      </span>
      <br />
      <span class="p-float-label">
        <InputText
          id="disciplines"
          type="text"
          v-model="TeacherDTO.disciplines[0].disciplineName"
          style="width: 100%"
        />
        <label for="disciplines"> Nome da Disciplina </label>
      </span>
      <br />
      <span class="select">
        <select
          class="type"
          v-model="TeacherDTO.disciplines[0].type_of_teaching"
        >
          <option :value="null" disabled>Tipo de Ensino</option>
          <option>EAD</option>
          <option>Presencial</option>
        </select>
      </span>
      <br />
      <span class="p-float-label">
        <InputText
          onKeyDown="if(this.value.length==2) return false;"
          id="disciplines"
          type="number"
          min="1"
          max="80"
          v-model="TeacherDTO.disciplines[0].workload"
          style="width: 100%"
        />
        <label for="disciplines"> Carga Horária </label>
      </span>
      <template #footer>
        <Button label="Salvar" icon="pi pi-check" @click="save" />
        <Button
          label="Cancelar"
          icon="pi pi-times"
          @click="closeModal"
          class="p-button-secondary"
        />
      </template>
    </Dialog>
  </div>
</template>

<script>
import Service from "../service/Service";
export default {
  name: "Table",
  data() {
    return {
      messages: [],
      count: 0,
      personas: null,
      TeacherDTO: {
        id: null,
        name: null,
        registration: null,
        disciplines: [
          {
            disciplineName: null,
            type_of_teaching: null,
            workload: null,
          },
        ],
      },
      selectedTeacher: {
        id: null,
        name: null,
        registration: null,
        disciplines: [
          {
            disciplineName: null,
            type_of_teaching: null,
            workload: null,
          },
        ],
      },
      menu: [
        {
          label: "Formulário",
          icon: "pi pi-fw pi-book",
          url: "http://localhost:8080/",
        },
        {
          label: "Consultar Tabela",
          icon: "pi pi-fw pi-table",
          url: "http://localhost:8080/#/table",
        },
      ],
      itemsTeacher: [
        {
          label: "Editar",
          icon: "pi pi-fw pi-pencil",
          command: () => {
            this.showEditModal();
          },
        },
        {
          label: "Excluir",
          icon: "pi pi-fw pi-trash",
          command: () => {
            this.delete();
          },
        },
        {
          label: "Atualizar",
          icon: "pi pi-fw pi-refresh",
          command: () => {
            this.getAll();
          },
        },
      ],
      displayModal: false,
    };
  },
  service: null,
  created() {
    this.service = new Service();
  },
  mounted() {
    this.getAll();
  },
  methods: {
    ShowMessage() {
      this.messages = [
        {
          severity: "success",
          content: "Disciplina removida com sucesso!",
          id: this.count++,
        },
      ];
    },
    showSaveModal() {
      this.displayModal = true;
    },
    showEditModal() {
      this.TeacherDTO = this.selectedTeacher;
      this.displayModal = true;
    },
    getAll() {
      this.service.getAll().then((data) => {
        this.personas = data.data;
      });
    },
    delete() {
      this.service.delete(this.selectedTeacher.id).then((data) => {
        if (data.status === 204) {
          this.ShowMessage();
          this.getAll();
        }
      });
    },
    save() {
      this.service.save(this.TeacherDTO).then((data) => {
        if (data.status === 201) {
          this.getAll();
          (this.persona = {
            id: null,
            name: null,
            registration: null,
            disciplines: [
              {
                disciplineName: null,
                type_of_teaching: null,
                workload: null,
              },
            ],
          }),
            (this.displayModal = false);
        }
      });
    },
    closeModal() {
      this.displayModal = false;
    },
  },
};
</script>

<style>
.p-panel .p-panel-header {
  border: 1px solid #dee2e6;
  padding: 1rem;
  background: #f8f9fa;
  color: #495057;
  border-top-right-radius: 3px;
  border-top-left-radius: 3px;
  margin-bottom: 1.2rem;
  font-size: 24px;
}

.p-panel-content {
  display: none;
}

.p-datatable-wrapper {
  border-radius: 3px;
  border: 1px solid #dee2e6;
}

h2.h2-table {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica,
    Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
  color: #495057;
  text-align: center;
  font-size: 30px;
}
</style>