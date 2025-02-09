using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace BookingPaymentAPI.Models
{
    [Table("payment")] // Ensure EF maps this to the correct table
    public class Payment
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        [Column("payment_id")] // Ensure EF maps this to MySQL column name
        public int PaymentId { get; set; }

        [Column("booking_id")]
        public int BookingId { get; set; }

        [Column("amount_to_pay")]
        public decimal AmountToPay { get; set; }

        [Column("payment_date")]
        public DateOnly PaymentDate { get; set; }

        [Column("mode_id")]
        public int ModeId { get; set; }
    }
}
