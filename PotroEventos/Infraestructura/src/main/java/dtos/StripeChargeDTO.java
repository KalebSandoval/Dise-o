package dtos;

/**
 * DTO exclusivo de infraestructura para enviar datos de cobro a Stripe. Este
 * objeto no debe salir de la capa de infraestructura.
 *
 * @author Aaron Burciaga - 262788
 * @author Brian Sandoval - 262741
 * @author Dayanara Peralta - 262695
 * @author María Valdez - 262775
 */
public class StripeChargeDTO {

    /**
     * Monto a cobrar en centavos.
     */
    private Long amount;

    /**
     * Moneda del cobro.
     */
    private String currency;

    /**
     * Descripción del cobro.
     */
    private String description;

    /**
     * Token de Stripe asociado a la tarjeta.
     */
    private String source;

    /**
     * Constructor vacío.
     */
    public StripeChargeDTO() {
    }

    /**
     * Constructor completo.
     *
     * @param amount monto a cobrar
     * @param currency moneda
     * @param description descripción del cobro
     * @param source token de Stripe
     */
    public StripeChargeDTO(Long amount, String currency, String description, String source) {
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.source = source;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
