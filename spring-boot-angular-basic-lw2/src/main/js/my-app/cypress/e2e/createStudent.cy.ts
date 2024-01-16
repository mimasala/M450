describe('create new student and see it in list', () => {
  const newStudentName = 'Cypress User';
  const newStudentEmail = 'this.user@cypress.com'
  it('passes', () => {
    cy.visit('http://localhost:4200');
    cy.contains('Add Students').click();
    cy.get('input[name="name"').type(newStudentName);
    cy.get('input[name="email"').type(newStudentEmail);
    cy.contains('Submit').click();
    cy.visit('http://localhost:4200');
    cy.contains("List Students").click();

    cy.get('.table > tbody:nth-child(2) > tr:nth-child(6) > td:nth-child(2)')
      .should('contain', newStudentName);
  })
})
