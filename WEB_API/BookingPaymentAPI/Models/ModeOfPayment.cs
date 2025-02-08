using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace BookingPaymentAPI.Models
{
    [Table("mode_of_payment")]
    public class ModeOfPayment
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        [Column("mode_id")]
        public int ModeId { get; set; }

        [Required]
        [Column("mode_name")]
        public string ModeName { get; set; } = string.Empty;
    }
}
