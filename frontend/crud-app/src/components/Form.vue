<template>
  <div style="margin: 0 auto; width: 100%">
    <Menubar class="menu" :model="menu" />
    <br />
    <div class="message">
      <br />
      <transition-group name="p-message" tag="div">
        <Message
          v-for="msg of messages"
          :severity="msg.severity"
          :sticky="false"
          :life="3000"
          :key="msg.id"
          >{{ msg.content }}</Message
        >
      </transition-group>
      <br />
    </div>
    <Dialog
      class="registration-form"
      :visible.sync="displayModal"
      :modal="true"
    >
      <h2>Olá Professor(a), seja bem-vindo(a)!</h2>
      <p>Cadastre sua Disciplina abaixo:</p>
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
      </template>
    </Dialog>
  </div>
</template>

<script>
import Service from "../service/Service";
export default {
  name: "Form",
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
      displayModal: true,
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
          content: "Disciplina cadastrada com sucesso!",
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
            (this.displayModal = true);
          this.ShowMessage();
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
/* MODAL */
.registration-form.p-component-overlay-enter {
  -webkit-animation: auto !important;
  animation: none !important;
}

.registration-form.p-component-overlay {
  background-color: rgba(255, 255, 255, 0) !important;
  transition-duration: 0s !important;
}

.registration-form.p-dialog {
  box-shadow: none !important;
  border: 1px solid #ced4da !important;
}

.registration-form.p-dialog-mask.p-component-overlay.p-component-overlay-enter {
  z-index: -1 !important;
}
/* MODAL */

/* MENU */
.menu.p-menubar.p-component {
  padding: 0.5rem;
  background: white !important;
  color: #495057;
  border: 0 !important;
  border-radius: 3px;
  justify-content: center;
}
/* MENU */

/* MESSAGE */
.message {
  position: absolute;
  margin-left: auto;
  margin-right: auto;
  width: 24em;
}

.p-message.p-message-success .p-message-icon {
  color: #ffffff !important;
}

.p-message.p-message-success {
  background: #2196f3 !important;
  border: solid #0d66ad !important;
  border-width: 0 0 0 6px !important;
  color: #ffffff !important;
}

.p-message.p-message-success .p-message-close {
  color: #ffffff !important;
}
/* MESSAGE */

.p-dialog-content {
  border-radius: 3px 3px 0px 0px;
}

.p-dialog .p-dialog-header {
  display: none;
  /* justify-content: right; */
}

.p-dialog .p-dialog-footer {
  text-align: left !important;
}

select.type {
  display: block;
  position: relative;
}

select.type {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica,
    Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
  font-size: 1rem;
  color: #6c7592;
  background: #ffffff;
  padding: 0.5rem 0.5rem;
  border: 1px solid #ced4da;
  transition: background-color 0.2s, color 0.2s, border-color 0.2s,
    box-shadow 0.2s;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  border-radius: 3px;
  width: 100%;
}

.logo {
  text-align: center;
}

.p-panel .p-panel-content {
  text-align: center;
}
</style>